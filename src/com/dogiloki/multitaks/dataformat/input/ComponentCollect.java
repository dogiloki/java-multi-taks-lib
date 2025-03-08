package com.dogiloki.multitaks.dataformat.input;

import com.dogiloki.multitaks.dataformat.contracts.InputComponent;
import javax.swing.JComboBox;

/**
 *
 * @author _dogi
 */

public class ComponentCollect extends JComboBox implements InputComponent{

    @Override
    public String getText() {
        return this.getSelectedItem().toString();
    }

    @Override
    public void setText(String text) {
        this.setSelectedItem(text);
    }
    
}
