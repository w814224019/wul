
//根据网点id找网点memberBybId()
function memberBybId(){
	var mark=0;
	memberid(mark);
}
//根据公司id找公司信息
function memberById(){
	document.getElementById("yeshu").style.display='none';
	var mark=1;
	var url="../memberById";
	 var companyId=$.trim($("#companyId").val());
	 var tbody=window.document.getElementById("table-result");
	console.log("参数data:"+companyId);
	$.ajax({
		"url":url,
	  "data":{
			"companyId":companyId,
			"mark":mark,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				var data=json.data;
				console.log("查询成功！"+json.data);
				var str="";
				$("#company").text(str);
				str+="<tr class='text-c'>"+
				"<td align='center'><a  id="+data.companyId+">"+data.companyId+"</a></td>"+
				"<td align='center'><img  src='http://www.56114.net.cn/img/"+data.vip+".jpg'><a target='_blank'   href='../index4?companyId="+data.companyId+"'>"+data.companyName+"</a><a target='_blank'   href='"+data.mobilepath+"'><img src='http://static.56114.net.cn/images/ssj.jpg' width='9' height='15'/></a></td>"+
				"<td align='center'><img style='width:100%' onclick='javascript:window.open(this.src)' src="+data.detailPicture+"></img></td>"+
				"<td align='center'>"+data.address+"</td>"+
				"<td align='center'>"+data.contact+"</td>"+
				"<td align='center'>"+data.phone+"</td>"+
				"</tr>";	
				
				tbody.innerHTML=str;
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
//通用方法
function memberid(mark){
	document.getElementById("yeshu").style.display='none';
	var url="../memberById";
	 var companyId=$.trim($("#companyId").val());
	 
	 var tbody=window.document.getElementById("table-result");
	console.log("参数data:"+companyId);
	$.ajax({
		"url":url,
	  "data":{
			"companyId":companyId,
			"mark":mark,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				var data=json.data;
				console.log("查询成功！"+json.data);
				var str="";
				$("#company").text(str);
				str+="<tr class='text-c'>"+
				"<td align='center'><a  id="+data.companyId+">"+data.companyId+"</a></td>"+
				"<td align='center'><a target='_blank'   href='/index5?companyId="+data.companyId+"'>"+data.companyName+"</a></td>"+
				"<td align='center'><img style='width:100%' onclick='javascript:window.open(this.src)' src="+data.detailPicture+"></img></td>"+
				"<td align='center'>"+data.address+"</td>"+
				"<td align='center'>"+data.contact+"</td>"+
				"<td align='center'>"+data.phone+"</td>"+
				"</tr>";	
				tbody.innerHTML=str;
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}


 $(document).ready(function(){
	
	admin_permission_sbc();
	
}); 
//跳页admin_permission_sbc()


function admin_permission_sbc(){
		var url="../selusertocorb";
		var tbody=window.document.getElementById("table-result");
		 var pageNum=$.trim($("#pageNum").val());
		 var pageSize=$.trim($("#pageSize").val());
		$.ajax({
			"url":url,
		  "data":{
				"pageNum":pageNum,
				"pageSize":pageSize,
			},  
			"type":"get",
			"dataType":"json",
			"success":function(json){
				if(json.state==200){
					console.log("查询公司成功！"+json.data);
					var str="";
					var data=json.data;
					$("#company").text(str);
					for(i in data){
						if(data[i].mark==1){
							str+="<tr class='text-c'>"+
							"<td align='center'><a  id="+data[i].companyId+">"+data[i].companyId+"</a></td>"+
							"<td align='center'><img  src='http://www.56114.net.cn/img/"+data[i].vip+".jpg'><a     target='_blank'   href='../index4?companyId="+data[i].companyId+"'>"+data[i].companyName+"</a><a target='_blank'   href='"+data[i].mobilepath+"'><img src='http://static.56114.net.cn/images/ssj.jpg' width='9' height='15'/></a></td>"+
							"<td align='center'><img style='width:100%' onclick='javascript:window.open(this.src)' src="+data[i].detailPicture+"></img></td>"+
							"<td align='center'>"+data[i].address+"</td>"+
							"<td align='center'>"+data[i].contact+"</td>"+
							"<td align='center'>"+data[i].phone+"</td>"+
							"</tr>";
						}else{
							str+="<tr class='text-c'>"+
							"<td align='center'><a  id="+data[i].companyId+">"+data[i].companyId+"</a></td>"+
							"<td align='center'><a  target='_blank'   href='/index5?companyId="+data[i].companyId+"'>"+data[i].companyName+"</a></td>"+
							"<td align='center'></td>"+
							"<td align='center'>"+data[i].address+"</td>"+
							"<td align='center'>"+data[i].contact+"</td>"+
							"<td align='center'>"+data[i].phone+"</td>"+
							"</tr>";
						}
						
						
					}
					tbody.innerHTML=str;
					
					var total=0;
					var tiaoshu=0;
					if (data.length==0){
						total=0;
						tiaoshu=0;
					}else{
						
					 total=data[i].pages;
					 tiaoshu=data[i].totals;
					}
					$("#total").text(total);
					var option =""
						for(i=0;i<total;i++){
							//先创建好select里面的option元素
			                 option += "<option value='"+(i+1)+"' >"+(i+1)+"</option>";
						}
				    $('#pageNum').html(option);
				    $('#pageNum').val(pageNum);
					
					$("#tiaoshu").text(tiaoshu);
					
				}else if(json.state==402){
					alert(json.message)
				}
			}
		});

	
	
}






function datagjc(){
	var url="../product-brand2";
	 var Name=$.trim($("#companyLname").val());
	console.log("参数data:"+Name);
	$.ajax({
		"url":url,
	  "data":{
			"Name":Name,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				location.href="product-brand2";
			}else if(json.state==400){
				alert(json.message);
			}
		}
	});
}


function datafcn(){
	var url="../article-list2";
	 var companyName=$.trim($("#companyName").val());
	console.log("参数data:"+companyName);
	$.ajax({
		"url":url,
	  "data":{
			"companyName":companyName,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				location.href="product-brand";
			}else if(json.state==400){
				alert(json.message);
			}
		}
	});
}


function datafid(){
	var url="../article-list1";
	 var companyId=$.trim($("#companyId").val());
	console.log("参数data:"+companyId);
	$.ajax({
		"url":url,
	  "data":{
			"companyId":companyId,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				location.href="product-brand";
			}else if(json.state==400){
				alert(json.message);
			}
		}
	});
}


function datafx(){
	var url="../article-list";
	//var data=$("#form-s").serialize();
	 var sta=$.trim($("#from").attr("txt1"));
	var end=$.trim($("#to").attr("txt"));
	console.log("参数data:"+sta);
	$.ajax({
		"url":url,
	  "data":{
			"sta":end,
			"end":sta,
		},  
		//"data":data,
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				location.href="product-brand3";
			}else if(json.state==400){
				alert(json.message);
			}
		}
	});
}


function datacx(){
	var url="../article-list";
	//var data=$("#form-s").serialize();
	 var sta=$.trim($("#from").attr("txt1"));
	var end=$.trim($("#to").attr("txt"));
	console.log("参数data:"+sta);
	$.ajax({
		"url":url,
	  "data":{
			"sta":sta,
			"end":end,
		},  
		//"data":data,
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				location.href="product-brand3";
			}else if(json.state==400){
				alert(json.message);
			}
		}
	});
}
function zx(dom){
	console.log("点击")
		var url="../ToCategeroy3";
		//var data=$("#form-login").serialize();
		 var comp= dom.getAttribute("id");
		 console.log("参数comp:"+comp);
		$.ajax({
			"url":url,
		 "data":{"comp":comp}, 
			"type":"post",
			"dataType":"json",
			"success":function(json){
				if(json.state==200){
					console.log("物流公司查询成功！");
					window.open("index4");
			}else if(json.state==400){
				console.log(json.message);
				console.log("网点查询成功！");
				window.open("product-category5");
			}else if(json.state==202){
				console.log(json.message);
				console.log("网点查询成功！");
				window.open("product-category5");
			}
				
		}
		});
		}
$(document).ready(function(){
	

});	


		
	