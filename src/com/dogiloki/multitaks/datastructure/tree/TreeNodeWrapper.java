package com.dogiloki.multitaks.datastructure.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author _dogi
 */

public class TreeNodeWrapper extends DefaultMutableTreeNode{
    
    private String display_text;
    
    public TreeNodeWrapper(Object obj){
        super(obj);
        this.display_text=null;
    }
    
    public TreeNodeWrapper(Object obj, String display_text){
        super(obj);
        this.display_text=display_text;
    }
    
    @Override
    public String toString(){
        return this.display_text==null?this.getUserObject().toString():this.display_text;
    }
    
}
