package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.logger.AppLogger;
import com.dogiloki.multitaks.persistent.ExecutionObserver;
import com.dogiloki.multitaks.persistent.TaskProgress;
import javax.swing.border.TitledBorder;

/**
 *
 * @author _dogi
 */

public final class UpdaterDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdaterDialog.class.getName());

    private final Updater updater;
    private TaskProgress verification_task;
    private TaskProgress download_task;
    private TaskProgress apply_task;
    
    public UpdaterDialog(java.awt.Frame parent, boolean modal, String path){
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.updater=new Updater(path){
            @Override
            public void onProgress(){
                switch(this.getStatus()){
                    case CHECKING:{
                        loadVerificationTaksProgress();
                        update_download_btn.setVisible(false);
                        verification_task.setProgress(50);
                        break;
                    }
                    case UPDATE:{
                        update_download_btn.setVisible(true);
                        message_label.setText("Hay actualización disponible: "+this.getLastVersion());
                        verification_task.setProgress(100);
                        break;
                    }
                    case NO_UPDATE:{
                        update_download_btn.setVisible(false);
                        message_label.setText("No hay actualización disponible");
                        verification_task.setProgress(100);
                        break;
                    }
                    case DOWNLOADING:{
                        verification_task.setProgress(this.getPercentDownloads());
                        break;
                    }
                    case DOWNLOAD_COMPLETED:{
                        loadApplyTaskProgress();
                        break;
                    }
                    case APPLYING:{
                        apply_task.setProgress(this.getPercentApplyUpdate());
                        break;
                    }
                }
            }

            @Override
            public void onError(Exception ex){
                AppLogger.logger().showMessage();
                AppLogger.error(ex.getMessage());
            }

            @Override
            public void onComplete(){
                AppLogger.logger().showMessage();
                AppLogger.info("Actualización finalizada");
                dispose();
            }
        };
        this.update_download_btn.setVisible(false);
        this.setTitle(this.updater.getCurrentVersion());
        ExecutionObserver.EXECUTOR.submit(()->{
            this.updater.checkForUpdates();
        });
    }
    
    public void setTaksProgress(String title){
        TitledBorder border=(TitledBorder)this.panel.getBorder();
        border.setTitle(title);
        this.message_label.setText("");
        this.progress_bar.setValue(0);
        this.panel.repaint();
    }
    
    public void loadVerificationTaksProgress(){
        this.verification_task=new TaskProgress("Buscando actualización"){
            @Override
            public void onProgressChanged(){
                progress_bar.setValue(this.getProgress());
            }
        };
        this.setTaksProgress(this.verification_task.getName());
    }
    
    public void loadDownloadTaksProgress(){
        this.update_download_btn.setVisible(false);
        ExecutionObserver.EXECUTOR.submit(()->{
            this.updater.applyAfterDownload(true).downloadUpdate();
        });
        this.download_task=new TaskProgress("Descargando actualización - "+this.updater.getLastVersion()){
            @Override
            public void onProgressChanged(){
                progress_bar.setValue(this.getProgress());
            }
        };
        this.setTaksProgress(this.download_task.getName());
    }
    
    public void loadApplyTaskProgress(){
        ExecutionObserver.EXECUTOR.submit(()->{
            this.updater.applyUpdate();
        });
        this.apply_task=new TaskProgress("Descargando actualización - "+this.updater.getLastVersion()){
            @Override
            public void onProgressChanged(){
                progress_bar.setValue(this.getProgress());
            }
        };
        this.setTaksProgress(this.apply_task.getName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        progress_bar = new javax.swing.JProgressBar();
        progress_percent = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        message_label = new javax.swing.JLabel();
        update_download_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Válidar versión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        progress_bar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progress_barStateChanged(evt);
            }
        });

        progress_percent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        progress_percent.setText("0%");

        jScrollPane1.setViewportView(jTextPane1);

        message_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        message_label.setText("jLabel1");

        update_download_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        update_download_btn.setText("Descagar actualización");
        update_download_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_download_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(progress_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress_percent))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(message_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                        .addComponent(update_download_btn)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progress_percent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progress_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_download_btn)
                    .addComponent(message_label)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void update_download_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_download_btnActionPerformed
        this.loadDownloadTaksProgress();
    }//GEN-LAST:event_update_download_btnActionPerformed

    private void progress_barStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progress_barStateChanged
        this.progress_percent.setText(String.valueOf(this.progress_bar.getValue())+"%");
    }//GEN-LAST:event_progress_barStateChanged

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                UpdaterDialog dialog = new UpdaterDialog(new javax.swing.JFrame(),true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter(){
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e){
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel message_label;
    private javax.swing.JPanel panel;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JLabel progress_percent;
    private javax.swing.JButton update_download_btn;
    // End of variables declaration//GEN-END:variables
}
