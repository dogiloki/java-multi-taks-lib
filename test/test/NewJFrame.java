package test;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.dataformat.DataFormatViewer;
import com.dogiloki.multitaks.directory.Storage;
import database.Persona;

/**
 *
 * @author dogi_
 */

public class NewJFrame extends javax.swing.JFrame{
    
    public DataFormatViewer data_viewer;
    
    public NewJFrame(){
        initComponents();
        Persona persona=(Persona)new Persona().all().first();
        System.out.println(persona.toJson());
        this.data_viewer=new DataFormatViewer(persona);
        Function.setPanel(this.personas_panel,this.data_viewer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        personas_panel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        personas_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout personas_panelLayout = new javax.swing.GroupLayout(personas_panel);
        personas_panel.setLayout(personas_panelLayout);
        personas_panelLayout.setHorizontalGroup(
            personas_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        personas_panelLayout.setVerticalGroup(
            personas_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(personas_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personas_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Runtime runtime=Runtime.getRuntime();
        long initial_memory=runtime.totalMemory()-runtime.freeMemory();
        long initial_time=System.currentTimeMillis();
        /*
        Faker f=new Faker();
        for(int a=0; a<10; a++){
            Persona p=new Persona();
            p.nombre=f.name().firstName();
            p.apellido=f.name().lastName();
            p.edad=f.number().randomDigit();
            p.save();
        }
        */
        //Sorting.default_order_algorithm=OrderAlgorithm.INSERTION_SORT;
        RecordList<Persona> personas=new Persona().all();
        Persona p;
        int index=0;
        while((p=personas.next())!=null){
            System.out.println(index+" - "+p.nombre);
            p.nombre="a";
            p.save();
            index++;
        }
        long final_memory=runtime.totalMemory()-runtime.freeMemory();
        long used_memory=final_memory-initial_memory;
        long end_time=System.currentTimeMillis();
        System.out.println("Memoria usada: "+Storage.convertSize(used_memory));
        System.out.println("Tiempo: "+(end_time-initial_time));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.data_viewer.load();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel personas_panel;
    // End of variables declaration//GEN-END:variables
}
