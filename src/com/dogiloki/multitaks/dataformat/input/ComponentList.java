package com.dogiloki.multitaks.dataformat.input;

import com.dogiloki.multitaks.dataformat.contracts.InputComponent;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 *
 * @author julio.villanueva
 */

public class ComponentList extends JComboBox implements InputComponent{
    
    public ComponentList(String[] values){
        super(values);
    }
    
    @Override
    public String getText(){
        Object value=this.getSelectedItem();
        return value==null?"":value.toString();
    }
    
    @Override
    public void setText(String value){
        this.setSelectedItem(value);
    }
    
    @Override
    public JComponent getComponent(){
        return this;
    }
    
}