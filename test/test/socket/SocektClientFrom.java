package test.socket;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import multitaks.directory.FileBlock;
import multitaks.directory.Storage;
import multitaks.socket.SocketClient;

/**
 *
 * @author dogi_
 */

public class SocektClientFrom extends javax.swing.JFrame {

    private SocketClient client;
    
    public SocektClientFrom() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        box_port = new javax.swing.JTextField();
        btn_conect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        box_ip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        box_channel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        box_message = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        box_channel_on = new javax.swing.JTextField();
        btn_on = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        box_messages = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Port");

        box_port.setText("1234");

        btn_conect.setText("Conectarse");
        btn_conect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectActionPerformed(evt);
            }
        });

        jLabel4.setText("IP");

        box_ip.setText("192.168.1.73");

        jLabel3.setText("Canal");

        box_channel.setText("test");

        jLabel2.setText("Mensaje");

        box_message.setText("Hola desde cliente");

        btn_send.setText("Enviar");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        box_channel_on.setText("test");

        btn_on.setText("Recibir");
        btn_on.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_onActionPerformed(evt);
            }
        });

        box_messages.setColumns(20);
        box_messages.setRows(5);
        jScrollPane1.setViewportView(box_messages);

        jLabel6.setText("Canal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(172, 172, 172))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_ip, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_port, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_conect))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_channel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_message)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_send))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_channel_on)
                        .addGap(1, 1, 1)
                        .addComponent(btn_on))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(102, 102, 102)
                                .addComponent(jLabel2))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_conect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_send)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(box_message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(box_channel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_channel_on, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_on))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_conectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectActionPerformed
        try{
            this.client=new SocketClient(this.box_ip.getText(),Integer.parseInt(this.box_port.getText()));
            this.client.start();
            this.client.on("welcome",(message)->{
                try{
                    System.out.println(message);
                    /*
                    Storage s=new Storage("VID-20230210-WA0004.mp4");
                    FileBlock file=s.fileBlock(1024);
                    file.write((byte[])message);
                    */
                }catch(Exception ex){
                    
                }
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_conectActionPerformed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        try{
            this.client.emit(this.box_channel.getText(),this.box_message.getText());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_onActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_onActionPerformed
        try{
            this.client.on(this.box_channel.getText(),(message)->{
                this.box_messages.setText(this.box_messages.getText()+message+"\n");
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_onActionPerformed

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SocektClientFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_channel;
    private javax.swing.JTextField box_channel_on;
    private javax.swing.JTextField box_ip;
    private javax.swing.JTextField box_message;
    private javax.swing.JTextArea box_messages;
    private javax.swing.JTextField box_port;
    private javax.swing.JButton btn_conect;
    private javax.swing.JButton btn_on;
    private javax.swing.JButton btn_send;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
