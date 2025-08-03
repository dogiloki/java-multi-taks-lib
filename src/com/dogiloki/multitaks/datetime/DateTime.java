package com.dogiloki.multitaks.datetime;

import com.dogiloki.multitaks.Function;
import java.util.Calendar;

/**
 *
 * @author _dogi
 */

public class DateTime{
    
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;
    
    public DateTime(String str){
        this.build(str);
    }
    
    public DateTime(){
        this.build(Function.getDateTime());
    }
    
    public Calendar calendar(){
        return this.cal;
    }
    
    public void build(String str){
        this.cal=Calendar.getInstance();
        this.cal.setTime(Function.parseDateTime(str));
        this.day=this.cal.get(Calendar.DAY_OF_MONTH);
        this.month=this.cal.get(Calendar.MONTH)+1;
        this.year=this.cal.get(Calendar.YEAR);
        this.hour=this.cal.get(Calendar.HOUR_OF_DAY);
        this.minute=this.cal.get(Calendar.MINUTE);
        this.second=this.cal.get(Calendar.SECOND);
    }
    
    public String date(){
        return new StringBuilder()
                .append(String.format("%02d",this.day)).append("-")
                .append(String.format("%02d",this.month)).append("-")
                .append(this.year)
                .toString();
    }
    
    public String time(){
        return new StringBuilder()
                .append(String.format("%02d",this.hour)).append(":")
                .append(String.format("%02d",this.minute)).append(":")
                .append(String.format("%02d",this.second))
                .toString();
    }
    
    @Override
    public String toString(){
        return this.date()+" "+this.time();
    }
    
}
