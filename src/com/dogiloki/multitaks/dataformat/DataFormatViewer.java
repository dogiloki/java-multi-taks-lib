package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.dataformat.annotations.FieldFormat;
import com.dogiloki.multitaks.directory.ListFields;
import com.google.gson.annotations.Expose;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author _dogi
 */

public class DataFormatViewer<T> extends javax.swing.JPanel{

    public Map<String,JTextField> list_text=new HashMap<>();
    public ListFields<Field> list=new ListFields<>();
    public T data=null;
    
    public DataFormatViewer(){
        initComponents();
    }
    
    public void setList(ListFields<Field> list){
        this.list=list;
        this.load();
    }
    
    public void loadList(T data){
        try{
            this.data=data;
            Field[] fields=this.data.getClass().getFields();
            for(Field field:fields){
                Expose annot_key=field.getAnnotation(Expose.class);
                FieldFormat annot_format=field.getAnnotation(FieldFormat.class);
                if(!(annot_key instanceof Expose) || !(annot_format instanceof FieldFormat)){
                    continue;
                }
                this.list.put(field,field.get(this.data));
            }
            this.load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DataFormatViewer<T> load(){
        this.panel.removeAll();
        this.panel.updateUI();
        this.list_text.clear();
        int x=10, y=10;
        int width_total=this.scroll_panel.getWidth()-20-x, height_total=y;
        int width=width_total, height=50;
        
        for(Map.Entry<Field,Object> entry:this.list.entrySet()){
            Field field=entry.getKey();
            Object value=entry.getValue();
            String label=field.getAnnotation(FieldFormat.class).label();
            String key=field.getName();
            try{
                Panel panel=new Panel();
                panel.setLayout(null);
                panel.setBounds(x,y,width,height);
                
                JLabel jlabel=new JLabel(label);
                jlabel.setBounds(0,0,width,15);
                panel.add(jlabel);
                
                
                JTextField text=new JTextField((String)value);
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
                this.list_text.put(key,text);
                
                y+=height;
                height_total=y;
                this.panel.add(panel);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        this.panel.updateUI();
        this.panel.setPreferredSize(Function.createDimencion(width_total,height_total));
        
        return this;
    }
    
    public void clean(){
        for(Map.Entry<String,JTextField> entry:this.list_text.entrySet()){
            entry.getValue().setText("");
        }
    }
    
    public void save(){
        for(Map.Entry<String,JTextField> entry:this.list_text.entrySet()){
            String key=entry.getKey();
            Object value=entry.getValue().getText();
            try{
                if(this.data!=null){
                    Field field=this.data.getClass().getDeclaredField(key);
                    field.set(this.data,(String)value);
                    this.list.put(field,value);
                }
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
