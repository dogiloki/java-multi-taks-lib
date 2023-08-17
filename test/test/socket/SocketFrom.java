package test.socket;

import java.net.Socket;
import javax.swing.JOptionPane;
import multitaks.socket.SocketClient;
import multitaks.socket.SocketHandle;
import multitaks.socket.SocketServer;

/**
 *
 * @author dogi_
 */

public class SocketFrom extends javax.swing.JFrame {

    SocketHandle socket;
    
    public SocketFrom(){
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        box_port_create = new javax.swing.JTextField();
        btn_create = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        box_message = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        box_channel_on = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        box_ip_conect = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        box_port_conect = new javax.swing.JTextField();
        btn_conect = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        box_channel_send = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        box_messages = new javax.swing.JTextArea();
        btn_on2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Puerto");

        box_port_create.setText("1234");

        btn_create.setText("Crear servidor");
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });

        jLabel2.setText("Mensaje");

        box_message.setText("Hola desde ");

        btn_send.setText("Enviar");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        box_channel_on.setText("test");

        jLabel4.setText("IP");

        box_ip_conect.setText("192.168.1.73");

        jLabel5.setText("Port");

        box_port_conect.setText("1234");

        btn_conect.setText("Conectarse");
        btn_conect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectActionPerformed(evt);
            }
        });

        jLabel3.setText("Canal");

        box_channel_send.setText("test");

        jLabel6.setText("Canal");

        box_messages.setColumns(20);
        box_messages.setRows(5);
        jScrollPane1.setViewportView(box_messages);

        btn_on2.setText("Recibir");
        btn_on2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_on2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(box_port_create)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_create))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(172, 172, 172))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_ip_conect, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_port_conect, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_conect))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_channel_send, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_message)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_send))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(box_channel_on)
                        .addGap(1, 1, 1)
                        .addComponent(btn_on2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_port_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_create))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_ip_conect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box_port_conect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_conect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_send)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(box_message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(box_channel_send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_channel_on, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_on2))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
        try{
            this.socket=new SocketServer(Integer.parseInt(this.box_port_create.getText()));
            this.socket.start();
            this.socket.on("connected",(client)->{
                try{
                    this.socket.emit((Socket)client,"welcome","Bienvenido "+((Socket)client));
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_createActionPerformed

    private void btn_conectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectActionPerformed
        try{
            this.socket=new SocketClient(this.box_ip_conect.getText(),Integer.parseInt(this.box_port_conect.getText()));
            this.socket.start();
            this.socket.on("welcome",(message)->{
                JOptionPane.showMessageDialog(null,message);
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_conectActionPerformed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        try{
            this.socket.emit(this.box_channel_send.getText(),this.box_message.getText());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_onActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send1ActionPerformed
        try{
            this.socket.on(this.box_channel_on.getText(),(message)->{
                this.box_messages.setText(this.box_messages.getText()+"["+this.box_channel_on.getText()+"]"+message.toString()+"\n");
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_send1ActionPerformed

    private void btn_on2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_on2ActionPerformed
        try{
            this.socket.on(this.box_channel_on.getText(),(message)->{
                this.box_messages.setText(this.box_messages.getText()+"["+this.box_channel_on.getText()+"]"+message.toString()+"\n");
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_on2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SocketFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SocketFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SocketFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SocketFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SocketFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_channel_on;
    private javax.swing.JTextField box_channel_send;
    private javax.swing.JTextField box_ip_conect;
    private javax.swing.JTextField box_message;
    private javax.swing.JTextArea box_messages;
    private javax.swing.JTextField box_port_conect;
    private javax.swing.JTextField box_port_create;
    private javax.swing.JButton btn_conect;
    private javax.swing.JButton btn_create;
    private javax.swing.JButton btn_on2;
    private javax.swing.JButton btn_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
