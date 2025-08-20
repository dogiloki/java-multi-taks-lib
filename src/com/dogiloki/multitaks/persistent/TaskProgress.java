package com.dogiloki.multitaks.persistent;

import com.dogiloki.multitaks.persistent.enums.TaskStatus;

/**
 *
 * @author _dogi
 */

public class TaskProgress{
    
    private final String name;
    private int progress;
    private TaskStatus _status;
    private ListTaskProgress subtasks=new ListTaskProgress();
    
    public TaskProgress(String name){
        this.name=name;
        this.setStatus(TaskStatus.PENDING);
    }
    
    public synchronized TaskProgress setProgress(int value){
        this.progress=Math.max(0,Math.min(100,value));
        if(this.progress>=100){
            this.setStatus(TaskStatus.COMPLETED);
        }else{
            this.setStatus(TaskStatus.RUNNING);
        }
        return this;    
    }
    
    private synchronized TaskProgress setStatus(TaskStatus status){
        this._status=status;
        return this;
    }
    
    public synchronized int getProgress(){
        if(this.getSubtasks().isEmpty()){
            return this.progress;
        }
        // Promedio de subprocesos
        int total=0;
        for(TaskProgress sub:this.getSubtasks()){
            total+=sub.getProgress();
        }
        return total/this.getSubtasks().size();
    }
    
    public String getName(){
        return this.name;
    }
    
    public TaskStatus getStatus(){
        return this._status;
    }
    
    public ListTaskProgress getSubtasks(){
        return this.subtasks;
    }
    
}
