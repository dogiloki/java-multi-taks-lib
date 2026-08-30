package com.dogiloki.multitaks.dataformat.contracts;

import javax.swing.JComponent;

/**
 *
 * @author _dogi
 */

public interface InputComponent{
    
    public abstract String getText();
    public abstract void setText(String text);
    public JComponent getComponent();
    
}
