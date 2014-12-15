//进入页面后就可以开始从服务器端获取数据，然后显示股票价格
$(document).ready(function(){
	getInfo();
	//每隔一秒钟和服务器交互一次，用户不刷新页面就可以不断的看到最新的股票信息
	setInterval(getInfo,1000);
		
});

//从服务器端获取数据并显示在页面上的方法
function getInfo(){
	//1.向服务器发起请求，获取数据
	$.get("GetStockInfo",null,function(data){
		//2.接受并解析数据
		var obj = eval(data);
		//2.1获取两只股票的当前指数信息
		var szzs = obj["000001"];  //obj.000001
		var snys = obj["002024"];
		/*
		           遍历一个js对象
			for(var stockid in obj){
				var stock = obj[stockid]; 
			} 
		 */
		//找到页面中对应的节点，然后填充最新股票价格
		var span3 = $("#000001").children("span").html(szzs.now);
		if(szzs.now > szzs.ye){
			//当前价格大于昨天收盘，则显示红色
			span3.css("color","red");
		} else{
			span3.css("color","green");
		}
		var span1 = $("#002024").children("span").html(snys.now);
		if(snys.now > snys.ye){
			//当前价格大于昨天收盘，则显示红色
			span1.css("color","red");
		} else{
			span1.css("color","green");
		}
	})
}