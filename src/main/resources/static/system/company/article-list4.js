//每页多少条
function GaiBian1(){
	var type=$('#select option:selected').val();
	var pageNum=$('#pageNum1 option:selected').val();
	var name=$.trim($("#companyLname").val());
	location.href="article-list4?pageNum="+pageNum+"&&pageSize="+type+"&&name="+name+""
	
}



function zx(dom){
	console.log("点击")
		var url="../ToCategeroy2";
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
					window.open("product-category2");
			}else if(json.state==402){
				alert(json.message);
			}
		}
		});
		}