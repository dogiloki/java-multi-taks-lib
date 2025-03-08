package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.dataformat.input.ComponentText;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.dataformat.annotations.FieldFormat;
import com.dogiloki.multitaks.dataformat.contracts.InputComponent;
import com.dogiloki.multitaks.dataformat.input.ComponentCollect;
import com.dogiloki.multitaks.directory.ListFields;
import com.google.gson.annotations.Expose;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author _dogi
 */

public class DataFormatViewer<T> extends javax.swing.JPanel{

    public Map<String,InputComponent> list_input_components=new HashMap<>();
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
                field.set(this.data,field.get(this.data));
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
        this.list_input_components.clear();
        int x=10, y=10;
        int width_total=this.scroll_panel.getWidth()-20-x, height_total=y;
        int width=width_total, height=50;
        
        for(Map.Entry<Field,Object> entry:this.list.entrySet()){
            Field field=entry.getKey();
            Object value=entry.getValue();
            FieldFormat annotation=field.getAnnotation(FieldFormat.class);
            String label=annotation.label();
            String key=field.getName();
            try{
                Panel panel=new Panel();
                panel.setLayout(null);
                panel.setBounds(x,y,width,height);
                
                JLabel jlabel=new JLabel(label);
                jlabel.setBounds(0,0,width,15);
                panel.add(jlabel);
                
                switch(annotation.type()){
                    case TEXT:{
                        ComponentText component=new ComponentText((String)value);
                        component.setBounds(0,15,width,25);
                        component.addFocusListener(new FocusListener(){
                            @Override
                            public void focusGained(FocusEvent evt){
                                
                            }
                            @Override
                            public void focusLost(FocusEvent evt){
                                save();
                            }
                        });
                        panel.add(component);
                        this.list_input_components.put(key,component);
                        break;
                    }
                    case COLLECT:{
                        Class<? extends ModelDB> model=annotation.collect();
                        ComponentCollect component=new ComponentCollect();
                        component.setBounds(0,15,width,25);
                        component.setLightWeightPopupEnabled(false);
                        component.setRecordList(model.newInstance().all());
                        component.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent evt){
                                String[] fields=annotation.fill_fields();
                                for(String field:fields){
                                    String[] name=field.split("=");
                                    String name_model=name[0];
                                    String name_dto=name[1];
                                    try{
                                        list_input_components.get(name_model).setText(component.getSelectedRecord().getFields().get(name_dto).toString());
                                    }catch(Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                                save();
                            }
                        });
                        panel.add(component);
                        this.list_input_components.put(key,component);
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
        
        this.panel.setPreferredSize(Function.createDimencion(width_total,height_total));
        this.panel.updateUI();
        this.updateUI();
        
        return this;
    }
    
    public void clean(){
        
        for(Map.Entry<String,InputComponent> entry:this.list_input_components.entrySet()){
            entry.getValue().setText("");
        }
    }
    
    public void save(){
        for(Map.Entry<String,InputComponent> entry:this.list_input_components.entrySet()){
            String key=entry.getKey();
            try{
                if(this.data!=null){
                    Field field=this.data.getClass().getDeclaredField(key);
                    FieldFormat annotation=field.getAnnotation(FieldFormat.class);
                    Object value=null;
                    switch(annotation.type()){
                        case TEXT:{
                            value=entry.getValue().getText();
                            break;
                        }
                        case COLLECT:{
                            value=((ComponentCollect)entry.getValue()).getSelectedRecord().getId();
                            break;
                        }
                    }
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
