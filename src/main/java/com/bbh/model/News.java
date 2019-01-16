package com.bbh.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Table(name="news",uniqueConstraints={@UniqueConstraint(name="headline_contraint", columnNames={"author"})})
public class News {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique=true)
	private int newsHash;
	
	@Column(columnDefinition="LONGTEXT")
	private String content;
	
	private String headline, imageUrl,sourceUrl, author, month, year, day;
	
	
	@ManyToOne(targetEntity=NewsPaper.class)
	private NewsPaper newsPaper;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	private Time time;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public NewsPaper getNewsPaper() {
		return newsPaper;
	}
	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getNewsHash() {
		return newsHash;
	}
	public void setNewsHash(int newsHash) {
		this.newsHash = newsHash;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	
	
	
}
