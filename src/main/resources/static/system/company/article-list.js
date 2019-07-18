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
	//反向查询
	function datafx(){
		if(localStorage.getItem("cityFromData") != null){
			let cityFromData2=localStorage.getItem("cityFromData");    //获取存储的信息，也是字符串格式
			let cityFromData3=JSON.parse(cityFromData2);
			document.getElementById("to").value = cityFromData3['fromCity'];
			$('#to').attr('txt', cityFromData3['fromId']);
		};
		if(localStorage.getItem("cityToData") != null){
			let cityToData2=localStorage.getItem("cityToData");    //获取存储的信息，也是字符串格式
			let cityToData3=JSON.parse(cityToData2);
			document.getElementById("from").value = cityToData3['toCity'];
			$('#from').attr('txt1', cityToData3['toId']);
		};
		datacx();
	}
	//会员图片
	$(".img1").attr("src", "http://www.56114.net.cn/img/诚信会员.jpg");
	$(".img2").attr("src", "http://www.56114.net.cn/img/金牌会员.jpg");
	$(".img3").attr("src", "http://www.56114.net.cn/img/银牌会员.jpg");
	$(".img4").attr("src", "http://www.56114.net.cn/img/铜牌会员.jpg");
	$(".img5").attr("src", "http://www.56114.net.cn/img/普通会员.jpg");
	$(".img6").attr("src", "http://www.56114.net.cn/img/试用会员.jpg");
	$(".img7").attr("src", "http://www.56114.net.cn/img/非会员.jpg");


	//按下回车登录
	document.onkeydown = function (e) {
	    var theEvent = window.event || e;
	    var code = theEvent.keyCode || theEvent.which;
	    if (code== 13)
	    {
	$("#btn-skey").click();
	    }
	}
	//每页多少条
	function GaiBian1(){
		var type=$('#select option:selected').val();
		var pageNum=$('#pageNum1 option:selected').val();
		location.href="article-list?pageNum="+pageNum+"&&pageSize="+type+""
		
	}


	//根据出发地和目的地查询
	function datacx(){
		var sta=$("#from").attr("txt1");
		var end=$("#to").attr("txt");
		datacx1(sta,end);
	}

	//专线查询不跳页
	function datacx1(sta,end){
		document.getElementById("zhuanxianbiao").style.display='block';  
		 document.getElementById("chushibiao").style.display='none';
		 var url="../article-list";
		 var tbody=window.document.getElementById("table-result");
			 var pageNum=$.trim($("#pageNum").val());
			 var pageSize=$.trim($("#pageSize").val());
			$.ajax({
				"url":url,
			  "data":{
					"pageNum":pageNum,
					"pageSize":pageSize,
					"sta":sta,
					"end":end,
				},  
				"type":"post",
				"dataType":"json",
				"success":function(json){
					if(json.state==200){
						console.log("查询公司成功！");
						var str="";
						var data=json.data;
						$("#company").text(str);
						for(i in data){
							if(data[i].mark==1){
								console.log(data[i].mobilepath+"手机网站")
								str+="<tr class='text-c'>"+
								"<td align='center'><a  id="+data[i].companyId+">"+data[i].companyId+"</a></td>"+
								"<td align='center'><img  src='http://www.56114.net.cn/img/"+data[i].vip+".jpg'><a  style='color: blue;'   target='_blank'   href='../index4?companyId="+data[i].companyId+"'>"+data[i].companyName+"</a><a target='_blank'   href='"+data[i].mobilepath+"'><img src='http://static.56114.net.cn/images/ssj.jpg' width='9' height='15'/></a></td>"+
								"<td align='center'><img style='width:100%' onclick='javascript:window.open(this.src)' src="+data[i].detailPicture+"></img></td>"+
								"<td align='center'>"+data[i].address+"</td>"+
								"<td align='center'>"+data[i].contact+"</td>"+
								"<td align='center'>"+data[i].phone+"</td>"+
								"</tr>";
							}else{
								str+="<tr class='text-c'>"+
								"<td align='center'><a  id="+data[i].companyId+">"+data[i].companyId+"</a></td>"+
								"<td align='center'><a  target='_blank' style='color: blue;'   href='/index5?companyId="+data[i].companyId+"'>"+data[i].companyName+"</a></td>"+
								"<td align='center'></td>"+
								"<td align='center'>"+data[i].address+"</td>"+
								"<td align='center'>"+(data[i].contact==null?'':data[i].contact)+"</td>"+
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


	//根据地区所在地模糊查找公司
	function datacxc(){
		var url="../article-list4";
		 var address=$.trim($("#from").val());
		console.log("参数data:"+address);
		$.ajax({
			"url":url,
		  "data":{
				"address":address,
			},  
			"type":"post",
			"dataType":"json",
			"success":function(json){
				if(json.state==200){
					console.log("查询成功！");
					location.href="product-brand4";
				}else if(json.state==400){
					alert(json.message);
				}
			}
		});
	}
	//关键词查找
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


	//根据公司id找
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



	//点击公司名称或图片跳转到公司详情
	function zx(dom){
		console.log("点击")
			var url="../ToCategeroy";
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
						console.log("查询成功！");
						window.open("index4");
				}
			}
			});
			}