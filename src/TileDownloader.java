import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import com.google.gson.*;

import static java.lang.Math.*;

public class TileDownloader extends JFrame {
    private JTextField zoomField, lat1Field, lon1Field, lat2Field, lon2Field;
    private JTextField x1Field, x2Field, y1Field, y2Field;
    private JProgressBar progressBar;
    private JTextArea logArea;

    private ExecutorService executor;
    private List<Map<String, Object>> failedTiles = Collections.synchronizedList(new ArrayList<>());

    public TileDownloader() {
        setTitle("Descargar Tiles de México");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de controles
        JPanel controls = new JPanel(new GridLayout(0, 4, 5, 5));
        zoomField = createField(controls, "Zoom:", "10");
        lat1Field = createField(controls, "Latitud inicio:", "32.444885");
        lon1Field = createField(controls, "Longitud inicio:", "-117.246094");
        lat2Field = createField(controls, "Latitud fin:", "14.040675");
        lon2Field = createField(controls, "Longitud fin:", "-85.979007");

        JButton calcButton = new JButton("Calcular Tiles");
        controls.add(calcButton);
        controls.add(new JLabel());

        x1Field = createField(controls, "X desde:", "");
        x2Field = createField(controls, "hasta:", "");
        y1Field = createField(controls, "Y desde:", "");
        y2Field = createField(controls, "hasta:", "");

        JButton downloadButton = new JButton("Descargar Tiles");
        controls.add(downloadButton);
        controls.add(new JLabel());

        add(controls, BorderLayout.NORTH);

        // Área de progreso y log
        JPanel bottomPanel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar();
        bottomPanel.add(progressBar, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        bottomPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.CENTER);

        // Eventos
        calcButton.addActionListener(e -> calculateTiles());
        downloadButton.addActionListener(e -> new Thread(this::downloadTiles).start());
    }

    private JTextField createField(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField(value);
        panel.add(field);
        return field;
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    private int[] latLngToTile(double lat, double lon, int zoom) {
        int tileCount = 1 << zoom;
        int x = (int) floor((lon + 180) / 360 * tileCount);
        double latRad = toRadians(lat);
        int y = (int) floor((1 - Math.log(tan(latRad) + 1 / cos(latRad)) / PI) / 2 * tileCount);
        return new int[]{x, y};
    }

    private void calculateTiles() {
        int zoom = Integer.parseInt(zoomField.getText());
        double lat1 = Double.parseDouble(lat1Field.getText());
        double lon1 = Double.parseDouble(lon1Field.getText());
        double lat2 = Double.parseDouble(lat2Field.getText());
        double lon2 = Double.parseDouble(lon2Field.getText());

        int[] start = latLngToTile(lat1, lon1, zoom);
        int[] end = latLngToTile(lat2, lon2, zoom);

        x1Field.setText(String.valueOf(Math.min(start[0], end[0])));
        x2Field.setText(String.valueOf(Math.max(start[0], end[0])));
        y1Field.setText(String.valueOf(Math.min(start[1], end[1])));
        y2Field.setText(String.valueOf(Math.max(start[1], end[1])));

        log("Rango de tiles calculado.");
    }

    private void downloadTiles() {
        failedTiles.clear();

        String logName = JOptionPane.showInputDialog(this, "Nombre del log de errores:");
        if (logName == null || logName.trim().isEmpty()) {
            log("Cancelado por el usuario.");
            return;
        }

        try {
            int zoom = Integer.parseInt(zoomField.getText());
            int x1 = Integer.parseInt(x1Field.getText());
            int x2 = Integer.parseInt(x2Field.getText());
            int y1 = Integer.parseInt(y1Field.getText());
            int y2 = Integer.parseInt(y2Field.getText());

            int total = (x2 - x1 + 1) * (y2 - y1 + 1);
            progressBar.setMaximum(total);
            progressBar.setValue(0);
            log("Iniciando descarga de " + total + " tiles...");

            int cores = Runtime.getRuntime().availableProcessors();
            executor = Executors.newFixedThreadPool(cores);
            CountDownLatch latch = new CountDownLatch(total);

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    final int fx = x;
                    final int fy = y;

                    executor.submit(() -> {
                        try {
                            String urlStr = String.format(
                                    "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/%d/%d/%d.png",
                                    zoom, fy, fx);
                            String folder = String.format("tiles/%d/%d", zoom, fx);
                            String filename = String.format("%s/%d.png", folder, fy);

                            Files.createDirectories(Paths.get(folder));

                            try (InputStream in = new URL(urlStr).openStream();
                                 OutputStream out = new FileOutputStream(filename)) {
                                in.transferTo(out);
                                log("Tile guardado: " + filename);
                            } catch (IOException ex) {
                                log(String.format("Error descargando tile %d/%d/%d", zoom, fx, fy));
                                Map<String, Object> error = new HashMap<>();
                                error.put("zoom", zoom);
                                error.put("x", fx);
                                error.put("y", fy);
                                failedTiles.add(error);
                            }
                        } catch (Exception ex) {
                            log("Error inesperado: " + ex.getMessage());
                        } finally {
                            progressBar.setValue(progressBar.getValue() + 1);
                            latch.countDown();
                        }
                    });
                }
            }

            latch.await();
            executor.shutdown();

            if (!failedTiles.isEmpty()) {
                Path logPath = Paths.get("logs", logName + ".json");
                Files.createDirectories(logPath.getParent());
                try (Writer writer = Files.newBufferedWriter(logPath)) {
                    new GsonBuilder().setPrettyPrinting().create().toJson(failedTiles, writer);
                    log("Log de errores guardado en: " + logPath);
                }
            }

            log("Descarga finalizada.");
        } catch (Exception e) {
            log("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TileDownloader().setVisible(true));
    }
}
