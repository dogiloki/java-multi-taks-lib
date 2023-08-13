package multitaks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Download extends javax.swing.JPanel implements Runnable{
    
    private String path;
    private String name;
    private InputStream in;
    private OutputStream out;
    private String url;
    private Thread thread;
    private boolean abort;
    private boolean pause;
    private boolean delete_if_canceled;
    
    public Download(String url, String path){
        this.init(url,path,Storage.getName(path));
    }
    
    public Download(String url, String path, String name){
        this.init(url,path+"/"+name,name);
    }
    
    private void init(String url, String path, String name){
        initComponents();
        this.path=Storage.formatPath(path);
        this.name=name;
        this.url=url;
        this.deleteIfCanceled(false);
        this.text_progress.setText(this.url);
        this.abort();
    }
    
    public void deleteIfCanceled(boolean op){
        this.delete_if_canceled=op;
    }
    
    public boolean deleteIfCanceled(){
        return this.delete_if_canceled;
    }
    
    public void abort(){
        this.thread=new Thread(this);
        this.resumen();
        this.btn_pause_resumen.setVisible(false);
        this.btn_play_canceled.setText("Iniciar");
        this.abort=true;
    }
    
    public void start(){
        this.btn_pause_resumen.setVisible(true);
        this.btn_play_canceled.setText("Cancelar");
        this.abort=false;
        this.resumen();
        this.thread.start();
    }
    
    public void pause(){
        this.btn_pause_resumen.setText("Reanudar");
        this.pause=true;
    }
    
    public void resumen(){
        this.btn_pause_resumen.setText("Pausar");
        this.pause=false;
    }
    
    @Override
    public void run(){
        File file=new File(this.path);
        this.progress.setMaximum(100);
        try{
            URLConnection con=new URL(this.url).openConnection();
            con.connect();
            
            this.in=new BufferedInputStream(con.getInputStream());
            this.out=new FileOutputStream(file,true);
            
            int size_total=con.getContentLength();
            int b=0;
            long start_time=System.currentTimeMillis();
            while(b!=-1){
                if(this.pause){
                    continue;
                }
                if(this.abort){
                    this.in.close();
                    this.out.close();
                    b=-1;
                    if(this.deleteIfCanceled()){
                        Storage.deleteFile(this.path);
                    }
                }else{
                    b=this.in.read();
                    if(b!=-1){
                        this.out.write(b);
                    }
                }
                long current_time=System.currentTimeMillis();
                long elapsed_time=current_time-start_time;
                this.text_progress.setText("Descargando: "+Storage.convertSize(file.length())+" / "+Storage.convertSize(size_total));
                this.progress.setValue((int)(file.length()*100)/size_total);
            }
            this.in.close();
            this.out.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text_progress = new javax.swing.JLabel();
        progress = new javax.swing.JProgressBar();
        btn_play_canceled = new javax.swing.JButton();
        btn_pause_resumen = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));

        text_progress.setMaximumSize(new java.awt.Dimension(400, 0));

        btn_play_canceled.setText("Cancelar");
        btn_play_canceled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_play_canceledActionPerformed(evt);
            }
        });

        btn_pause_resumen.setText("Cancelar");
        btn_pause_resumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pause_resumenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_pause_resumen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_play_canceled)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(text_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_play_canceled)
                    .addComponent(btn_pause_resumen)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_play_canceledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_play_canceledActionPerformed
        if(this.abort){
            this.start();
        }else{
            this.abort();
        }
    }//GEN-LAST:event_btn_play_canceledActionPerformed

    private void btn_pause_resumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pause_resumenActionPerformed
        if(this.pause){
            this.resumen();
        }else{
            this.pause();
        }
    }//GEN-LAST:event_btn_pause_resumenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pause_resumen;
    private javax.swing.JButton btn_play_canceled;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel text_progress;
    // End of variables declaration//GEN-END:variables

}
