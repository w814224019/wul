//加载总页数
$(document).ready(function(){
	getallrecords();
	
});

//查询网点日志
function getallrecords(){
	//把日志表放里面
	var url="../getallrecords";
	var tbody=window.document.getElementById("table-result1");
	 var id=$.trim($("#id").val());
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
				console.log("查询网点日志成功！"+json.data);
				var str="";
				var data=json.data;
				$("#company1").text(str);
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
 //跳转批量增加线路gobcomapany()
 function gobcomapany(){
			var url="../gobcomapany";
			 var id=$.trim($("#id").val());
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
						location.href="article-list9";
					}else if(json.state==402){
						alert(json.message);
					}
				}
			});
		}

 
 
 //delbpaths()批量删除网点线路
 function delbpaths(){
	var url="../delbpaths";
	var arr=[];//定义一个数组
    var arr2=$("input[name='duoxuan']:checked");//获取多选框的数组
    $.each(arr2,function () {
        arr.push($(this).val());//把多选框数组的value放入自己定义的里面
    })
	console.log(arr);
	$.ajax({
		"url":url,
	  "data":{
		  "ids" : arr.join(",")
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				alert("删除成功！");
				//location.href="";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
 
 //updatebCompany()更新网点资料
 function updatebCompany(){
	var url="../updatebCompany";
	var s1 = document.getElementById('s1').value;
	 var s2 = document.getElementById('s2').value;
	 var s3 = document.getElementById('s3').value;
	 var branchrelation=$("#branchrelation option:selected ").val();
	 var id=$.trim($("#id").val());
	 var companyId=$.trim($("#companyId").val());
	 var branchName=$.trim($("#branchName").val());
	 var address=$.trim($("#address").val());
	 var phone=$.trim($("#phone").val());
	 var telephone=$.trim($("#telephone").val());
	 var content=$.trim($("#content").val());
	 var intro=$.trim($("#intro").val());
	 var culture=$.trim($("#culture").val());
	console.log("参数data:"+address);
	$.ajax({
		"url":url,
	  "data":{
		  "s1":s1,
		  "s2":s2,
		  "s3":s3,
		  "id":id,
		  "companyId":companyId,
		  "branchName":branchName,
		  "address":address,
		  "phone":phone,
		  "telephone":telephone,
		  "content":content,
		  "branchrelation":branchrelation,
		  "intro":intro,
		  "culture":culture,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("修改成功！");
				location.href="";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}

 //删除网点线路 delbPather()
 function delbPather(dom){
	var url="../delbPather";
	var id= dom.getAttribute("id");
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
				alert("删除成功！");
				//location.href="product-category2";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
 //充值功能
 function addmoney(){
	var url="../addmoney";
	 var id=$.trim($("#id").val());
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
				alert("充值成功！");
				location.href="product-category2?companyId="+id+"";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
 
 
 
 //设置单独线路刷新
 function setrefresh(dom){
	var url="../setrefresh";
	 var id=$.trim($("#id").val());
	 var xid= dom.getAttribute("id");
	console.log("参数data:"+id);
	$.ajax({
		"url":url,
	  "data":{
		  "id":id,
		  "xid":xid,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("成功！");
				window.open("blank");
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}

//标记删除delbCompany()
 function delbCompany(){
	var url="../delbCompany";
	 var id=$.trim($("#id").val());
	 
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
				alert("删除成功！");
				//location.href="article-list7";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
 
 
 
 
//物流币刷新功能
function rebCompany(){
	var url="../rebCompany";
	 var id=$.trim($("#id").val());
	 
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
				alert("刷新成功！");
				//location.href="article-list7";
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}
//网点添加线路
function dataNpath(){
	var url="../dNpath";
	var sta=$("#from").attr("txt1");
	var end=$("#to").attr("txt");
	 var id=$.trim($("#id").val());
	console.log("参数data:"+end);
	$.ajax({
		"url":url,
	  "data":{
		  
		  "cfd":sta,
		  "ddd":end,
		  "id":id,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				alert("添加成功！");
				location.href="";
			}else if(json.state==400){
				alert(json.message);
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

	
	$('#from').on('click', function() {
		let from5 = $('#from').attr('name');
		let from1={name:from5};
		let from2=JSON.stringify(from1);//将对象"序列化"为JSON数据(字符串格式)
		localStorage.setItem("name",from2);  //以字符串格式存储信息
		window.location.href = "product-brand7";
	});
	$('#to').on('click', function() {
		let to = $('#to').attr('name');
		let to1={name:to};
		let to2=JSON.stringify(to1);//将对象"序列化"为JSON数据(字符串格式)
		localStorage.setItem("name",to2);  //以字符串格式存储信息
		window.location.href = "product-brand7";
	});
	
	//插入数据
	if(localStorage.getItem("cityFromData") != null){
		let cityFromData2=localStorage.getItem("cityFromData");    //获取存储的信息，也是字符串格式
		let cityFromData3=JSON.parse(cityFromData2);
		document.getElementById("from").value = cityFromData3['fromCity'];
		$('#from').attr('txt1', cityFromData3['fromId']);
	};
	if(localStorage.getItem("cityToData") != null){
		let cityToData2=localStorage.getItem("cityToData");    //获取存储的信息，也是字符串格式
		let cityToData3=JSON.parse(cityToData2);
		document.getElementById("to").value = cityToData3['toCity'];
		$('#to').attr('txt', cityToData3['toId']);
	};