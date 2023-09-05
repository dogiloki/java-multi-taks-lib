package structure;

import com.dogiloki.multitaks.datastructure.graph.Graph;
import com.dogiloki.multitaks.datastructure.graph.dijkstra.DijkstraAlgorithm;

/**
 *
 * @author dogi_
 */

public class NewJFrame extends javax.swing.JFrame{
    
    public NewJFrame(){
        initComponents();
        Graph<String> g=new Graph();
        g.add("A");
        g.add("B");
        g.add("C");
        g.add("D");
        g.add("E");
        g.add("F");
        g.add("G");
        g.add("H");
        g.add("I");
        g.add("J");
        g.add("K");
        g.add("L");
        g.add("M");
        g.add("N");
        g.add("P");
        g.bothWaysEdge("A","B",8);
        g.bothWaysEdge("A","E",4);
        g.bothWaysEdge("A","D",5);
        g.bothWaysEdge("B","C",3);
        g.bothWaysEdge("B","F",4);
        g.bothWaysEdge("B","E",12);
        g.bothWaysEdge("C","F",9);
        g.bothWaysEdge("C","G",11);
        g.bothWaysEdge("D","E",9);
        g.bothWaysEdge("D","H",6);
        g.bothWaysEdge("E","F",3);
        g.bothWaysEdge("E","J",5);
        g.bothWaysEdge("E","I",8);
        g.bothWaysEdge("F","K",8);
        g.bothWaysEdge("F","G",1);
        g.bothWaysEdge("G","L",7);
        g.bothWaysEdge("H","I",2);
        g.bothWaysEdge("H","M",7);
        g.bothWaysEdge("I","J",10);
        g.bothWaysEdge("I","M",6);
        g.bothWaysEdge("J","K",6);
        g.bothWaysEdge("J","N",9);
        g.bothWaysEdge("K","L",5);
        g.bothWaysEdge("K","P",7);
        g.bothWaysEdge("L","P",6);
        g.bothWaysEdge("M","N",2);
        g.bothWaysEdge("N","P",12);
        DijkstraAlgorithm<String> d=new DijkstraAlgorithm(g);
        d.shortest("C","I").values().forEach((node)->{
            System.out.println(node);
        });
        for(int a=0; a<d.table().getRowCount(); a++){
            //System.out.println(d.table().vertex(a).getValue()+" - "+d.table().finalWeight(a));
        }
        this.table.setModel(d.table());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
