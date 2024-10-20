package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.dataformat.annotations.FieldFormat;
import com.google.gson.annotations.Expose;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author _dogi
 */

public class DataFormatViewer<T> extends javax.swing.JPanel{

    public Map<Field,JTextField> list_text=new HashMap<>();
    public T data;
    
    public DataFormatViewer(T data){
        initComponents();
        this.data=data;
    }
    
    public DataFormatViewer<T> load(){
        this.panel.removeAll();
        this.panel.revalidate();
        this.panel.repaint();
        this.list_text.clear();
        int x=10, y=10;
        int width_total=this.scroll_panel.getWidth()-20-x, height_total=y;
        int width=width_total, height=50;
        Field[] fields=this.data.getClass().getFields();
        
        for(Field field:fields){
            Expose annot_key=field.getAnnotation(Expose.class);
            FieldFormat annot_format=field.getAnnotation(FieldFormat.class);
            if(!(annot_key instanceof Expose) || !(annot_format instanceof FieldFormat)){
                continue;
            }
            Object value=null;
            try{
                value=field.get(this.data);
                
                Panel panel=new Panel();
                panel.setLayout(null);
                panel.setBounds(x,y,width,height);
                
                JLabel label=new JLabel(annot_format.label());
                label.setBounds(0,0,width,15);
                panel.add(label);
                
                switch(annot_format.type()){
                    case TEXT:{
                        JTextField text=new JTextField(value.toString());
                        text.setBounds(0,15,width,25);
                        text.addFocusListener(new FocusListener(){
                            @Override
                            public void focusGained(FocusEvent evt){
                                
                            }
                            @Override
                            public void focusLost(FocusEvent evt){
                                save();
                            }
                        });
                        panel.add(text);
                        this.list_text.put(field,text);
                        break;
                    }
                    case LIST:{
                        DefaultListModel model_list=new DefaultListModel<>();
                        Class<? extends Enum<?>> annot_list=annot_format.list();
                        for(Enum<?> value_list:annot_list.getEnumConstants()){
                            model_list.addElement(value_list);
                        }
                        JList list=new JList();
                        list.setBounds(0,15,width,50);
                        height+=25;
                        list.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
                        list.setModel(model_list);
                        panel.add(list);
                        break;
                    }
                }
                
                y+=height;
                height_total=y;
                this.panel.add(panel);
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        this.panel.revalidate();
        this.panel.repaint();
        this.panel.setPreferredSize(Function.createDimencion(width_total,height_total));
        
        return this;
    }
    
    public void save(){
        for(Map.Entry<Field,JTextField> entry:this.list_text.entrySet()){
            Field field=entry.getKey();
            JTextField text=entry.getValue();
            try{
                field.set(this.data,(String)text.getText());
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll_panel = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        scroll_panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scroll_panelComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        scroll_panel.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scroll_panelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_scroll_panelComponentResized
        this.load();
    }//GEN-LAST:event_scroll_panelComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll_panel;
    // End of variables declaration//GEN-END:variables
}
