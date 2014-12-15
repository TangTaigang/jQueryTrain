package com.itcast.jquery;
/*
 * 用于保存股票信息
 */
public class Stock {
	//昨天收盘价
	private double yestoday;
	//今天开盘价
	private double today;
	//当前价
	private double now;
	//股票名称
	private String name;
	//股票代码
	private String id;
	
	public Stock(double yestoday, double today, String name, String id) {
		super();
		this.yestoday = yestoday;
		this.today = today;
		this.name = name;
		this.id = id;
		this.now = today;
	}
	
	public double getYestoday() {
		return yestoday;
	}
	public void setYestoday(double yestoday) {
		this.yestoday = yestoday;
	}
	public double getToday() {
		return today;
	}
	public void setToday(double today) {
		this.today = today;
	}
	public double getNow() {
		return now;
	}
	public void setNow(double now) {
		this.now = now;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return this.name+" : "+this.now;
	}
}
