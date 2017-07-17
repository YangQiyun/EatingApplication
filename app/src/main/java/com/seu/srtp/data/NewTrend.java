package com.seu.srtp.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by swallowman on 2017/3/23.
 */

public class NewTrend implements Serializable {
    public int id;
    public String commenter;
    public String content;
    public Date date;
    public NewTrend(){

    }
    public NewTrend(int id, String commenter, String content){
        this.id=id;
        this.setCommenter(commenter);
        this.setContent(content);
        this.setDate();
    }
    public void setDate(){
        Calendar cal = Calendar.getInstance();
        date=cal.getTime();
    }
    public void setCommenter(String commenter){
        this.commenter=commenter;
    }
    public void setContent(String content){
        this.content=content;
    }
    public int getId(){
        return this.id;
    }
    public String getDate(){
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public String getCommenter(){
        return this.commenter;
    }
    public String getContent(){
        return this.content;
    }
}
