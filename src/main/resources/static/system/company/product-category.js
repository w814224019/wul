
/*用户-修改*/
function change_password(){
	var url="../ToCategeroy44";
	 var id=$.trim($("#companyId").val());
	 console.log("id"+id)
	$.ajax({
		"url":url,
	  "data":{
		  "id":id,
		},  
		"type":"get",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				member('验证密码','change-password','10001','600','510');
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
	
	
}
/*用户-添加*/
function member(title,url,id,w,h){
	layer_show(title,url,w,h);
}


//跳转批量增加线路gobcomapany()
function gocomapany(){
			var url="../gocomapany";
			 var id=$.trim($("#icompanyId").val());
			console.log("参数data:"+id);
			$.ajax({
				"url":url,
			  "data":{
				  "id":id,
				  
				},  
				"type":"post",
				"dataType":"json",
				"success":function(json){
					if(json.state==200){
						console.log("跳转成功！");
						location.href="article-list7";
					}else if(json.state==402){
						alert(json.message);
					}
				}
			});
		}








//公司充值物流币
function addmoney(){
	var url="../addcompanymoney";
	 var id=$.trim($("#companyId").val());
	// var balance=$.trim($("#balance").val());
	 var add=$.trim($("#add").val());
	console.log("参数data:"+id);
	$.ajax({
		"url":url,
	  "data":{
		  "id":id,
		//  "balance":balance,
		  "add":add,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				alert("公司充值成功！");
				location.href="product-category?companyId="+id+"";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}

//加载总页数
$(document).ready(function(){
	getallrecords();
	
});


//查询公司日志
function getallrecords(){
	//把日志表放里面
	var url="../getallrecords";
	var tbody=window.document.getElementById("table-result11");
	 var id=$.trim($("#companyId").val());
	 var pageNum=$.trim($("#pageNum").val());
	 var pageSize=$.trim($("#pageSize").val());
	console.log("参数data:"+id);
	$.ajax({
		"url":url,
	  "data":{
		  "id":id,
		  "pageNum":pageNum,
		  "pageSize":pageSize,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				//console.log("查询物流公司日志成功！"+json.data);
				var str="";
				var data=json.data;
				$("#company11").text(str);
				for(i in data){
					str+="<tr class='text-c'>"+
					"<td align='center'>"+data[i].id+"</td>"+
					"<td align='center'>"+data[i].modifiedUser+"</td>"+
					"<td align='center'>"+timeFormat(data[i].modifiedTime)+"</td>"+
					"<td align='center'>"+data[i].type+"</td>"+
					"</tr>";
						
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
				//location.href="product-category";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
	
	function timeFormat(time) {
	    var d = new Date(time);
	 
	    var year = d.getFullYear();       //年  
	    var month = d.getMonth() + 1;     //月  
	    var day = d.getDate();            //日  
	 
	    var hh = d.getHours();            //时  
	    var mm = d.getMinutes();          //分  
	    var ss = d.getSeconds();           //秒  
	 
	    var clock = year + "-";
	 
	    if (month < 10)
	        clock += "0";
	 
	    clock += month + "-";
	 
	    if (day < 10)
	        clock += "0";
	 
	    clock += day + " ";
	 
	    if (hh < 10)
	        clock += "0";
	 
	    clock += hh + ":";
	    if (mm < 10) clock += '0';
	    clock += mm + ":";
	 
	    if (ss < 10) clock += '0';
	    clock += ss;
	    return (clock);
	}
	
	
	
}

//查看网点列表
function findbcompany(){
	var url="../findbcompany";
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
				location.href="article-list3";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}


//更新公司资料
function updateCompany(){
	var url="../updateCompany";
	 var companyId=$.trim($("#companyId").val());
	 var companyName=$.trim($("#companyName").val());
	 var address=$.trim($("#address").val());
	 var phone=$.trim($("#phone").val());
	 var telephone=$.trim($("#telephone").val());
	 var contact=$.trim($("#contact").val());
	 var alias=$.trim($("#alias").val());
	 var depart=$.trim($("#depart").val());
	 var att=$('#testSelect option:selected').val()
	 var type=$('#select1 option:selected').val()
	 var style=$('#select2 option:selected').val()
	 var intro=$.trim($("#intro").val());
	 var culture=$.trim($("#culture").val());
	 var lightCost=$.trim($("#lightCost").val());
	 var heavyCost=$.trim($("#heavyCost").val());
	 var aging=$.trim($("#aging").val());
	 var directRemark=$.trim($("#directRemark").val());
	console.log("参数data:"+directRemark);
	$.ajax({
		"url":url,
	  "data":{
		  
		  "companyId":companyId,
		  "companyName":companyName,
		  "address":address,
		  "phone":phone,
		  "telephone":telephone,
		  "contact":contact,
		  "alias":alias,
			"depart":depart,
			"att":att,
			"intro":intro,
			"culture":culture,
			"type":type,
			"style":style,
			"lightCost":lightCost,
			"heavyCost":heavyCost,
			"aging":aging,
			"directRemark":directRemark,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				alert("修改成功!");
				location.href="";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}

//标记删除公司
function delCompany(){
	var url="../delCompany";
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
				alert("修改成功！");
				//location.href="article-list";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}



$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});
});