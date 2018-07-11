/*
 * 本类用于体现日记类
 * 作者：段佳伟
 * 时间：2017-06
 * 
 */
package jiawei.duan.write;



import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.text.Document;

public class Diary {
	private String date;
	
	private String uname;
	private String weather;
	private String mood;
	private String title;
	private Document content;
	
	public Diary() {
		
	}
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public String getMood() {
		return mood;
	}


	public void setMood(String mood) {
		this.mood = mood;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Document getContent() {
		return content;
	}


	public void setContent(Document content) {
		this.content = content;
	}


	public Diary(String date, String weather, String mood, String title, Document content) {
		
		this.date = date;
		//this.uname = uname;
		this.title = title;
		this.weather = weather;
		this.mood = mood;
		
		this.content = content;
	}

	
	@Override
	public String toString() {
		return "   " + title + "                                   " + weather + 
				              "                                    " + mood    + 
				              "                                 "+ date +" ";
	}
	
}

