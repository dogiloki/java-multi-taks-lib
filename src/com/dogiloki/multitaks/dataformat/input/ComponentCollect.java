package com.dogiloki.multitaks.dataformat.input;

import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.dataformat.contracts.InputComponent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author _dogi
 */

public class ComponentCollect extends JComboBox implements InputComponent{

    private Map<Integer,Record> index_record=new HashMap<>();
    
    public void setRecordList(RecordList<ModelDB> record_list){
        ModelDB record;
        int index=0;
        while((record=record_list.next())!=null){
            this.addItem(record.toString());
            this.index_record.put(index,record);
            index++;
        }
    }
    
    public Record getSelectedRecord(){
        return this.index_record.get(this.getSelectedIndex());
    }
    
    @Override
    public String getText() {
        return this.getSelectedItem().toString();
    }

    @Override
    public void setText(String text) {
        this.setSelectedItem(text);
    }
    
}
