package com.dogiloki.multitaks.dataformat.input;

import com.dogiloki.multitaks.dataformat.contracts.InputComponent;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author _dogi
 */

public class ComponentText extends JTextField implements InputComponent{
    
    public ComponentText(String label){
        super(label);
    }
    
    @Override
    public JComponent getComponent(){
        return this;
    }
    
}
