package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int num;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int is_completed;
    private String with_who;
    private String place;
    
    
    public String getWith_who() {
		return with_who;
	}

	public void setWith_who(String with_who) {
		this.with_who = with_who;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public TodoItem(String title, String category,String desc,String due_date,int com,String who, String place){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.category=category;
        this.due_date=due_date;
        this.is_completed=com;
        this.with_who=who;
        this.place=place;
	}
    
//
//    public TodoItem(String title, String desc,String date){
//        this.title=title;
//        this.desc=desc;
//     //   SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
//        this.current_date=date;
//    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    @Override
    public String toString() {
    	
    	if(is_completed==1) {
    	return id+". ["+this.desc+"] "+this.title+"[V] - "+ this.category+" - " +this.due_date + " - " + this.current_date+" - "+this.with_who+ " - "+this.place;
    	}
    	else {
    	return id+". ["+this.desc+"] "+this.title+" - "+ this.category+" - " + this.due_date + " - " +this.current_date+" - "+this.with_who+ " - "+this.place;
    	}c
    	
    }
    
    public String toSaveString() {
    	return  "##" + category+"##" + title + "##" + desc +  "##" + due_date +"##" + current_date + "\n";
    }
}
