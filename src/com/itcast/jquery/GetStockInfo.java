package com.itcast.jquery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//返回股票当前信息
public class GetStockInfo extends HttpServlet{
	//保存股票当前信息的map
	private HashMap<String,Stock> stocks;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.计算随机数
		double sz = Math.random() * 20;
		double sn = Math.random() *0.5;
		
		//通过随机数是奇数还是偶数来判断股票是涨还是跌
		boolean flagsz = ((int)(Math.random()*10)) % 2 == 0;
		boolean flagsn = ((int)(Math.random()*10)) % 2 == 0;
		
		//2.将随机数和股票当前价格进行加减的操作，得到当前价格
		Stock szzs = stocks.get("000001");
		Stock snys = stocks.get("002024");
		double temp;
		if(flagsz){
			temp = szzs.getNow() + sz;
			
		} else{
			temp = szzs.getNow() -sz;
		}
		//对当前价格进行截取，保留两位小数 
		temp = (int)(temp * 100) / 100.0;
		szzs.setNow(temp);
		if(flagsn){
			temp = snys.getNow() + sn;
			
		} else{
			temp = snys.getNow() - sn;
		}
		temp = (int)(temp * 100) / 100.0;
		snys.setNow(temp);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//out.print(szzs+"<br/>"+snys); 
		//3.返回两只股票的昨天收盘，今天开盘和当前价格
		//采用JSON的数据格式返回股票的信息
		/*[{
		name:"szzs.getName()",
		id:"szzs.getId()",
		ye:szzs.getYestoday(),
		to:szzs.getNow()},
	 	{
		name:"snys.getName()",
		id:"snys.getId()",
		ye:snys.getYestoday(),
		to:snys.getNow() }
		]*/
		
		StringBuilder builder = new StringBuilder();
		//采用数组的方式回传两个股票对象
		/*builder.append("[{name:\" ").append(szzs.getName()).append("\",id:\"").append(szzs.getId())
			   .append("\",ye:").append(szzs.getYestoday()).append(",to:").append(szzs.getToday())
			   .append(",now:").append(szzs.getNow())
			   .append("},")
			   .append("{name:\" ").append(snys.getName()).append("\",id:\"").append(snys.getId())
			   .append("\",ye:").append(snys.getYestoday()).append(",to:").append(snys.getToday())
			   .append(",now:").append(snys.getNow())
			   .append("}]");*/
		//采用对象的方式回传两个股票对象
		//如果回传表示对象的json，需要在最外层加上括号
		builder.append("({")
			   .append("\"").append(szzs.getId()).append("\":{name:\"").append(szzs.getName())
			   .append("\",ye:").append(szzs.getYestoday()).append(",to:").append(szzs.getToday())
			   .append(",now:").append(szzs.getNow())
			   .append("},")
			   .append("\"").append(snys.getId()).append("\":{name:\"").append(snys.getName())
		       .append("\",ye:").append(snys.getYestoday()).append(",to:").append(snys.getToday())
		       .append(",now:").append(snys.getNow())
		       .append("}})");
		out.print(builder);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		stocks = new HashMap<String,Stock>();
		Stock szzs = new Stock(3000.0,2990.1,"上证指数","000001");
		Stock snys = new Stock(7.5,25.2,"苏宁云商","002024");
		//将两支股票保存到Map集合中
		stocks.put(szzs.getId(), szzs);
		stocks.put(snys.getId(), snys);
		
		System.out.println(stocks);
	}
	
}
