//按下回车
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
	location.href="article-list2?pageNum="+pageNum+"&&pageSize="+type+""
	
}


//关键词查询
function findbcompayLn(){
	 var name=$.trim($("#name").val());
	console.log("参数data:"+name);
	location.href="article-list5?name="+name+"";
			
}

//通过网点id找网点
function datafid(){
	document.getElementById("label").style.display='none';
	document.getElementById("yeshu").style.display='none';
	var url="../ToCategeroy2";
	 var tbody=window.document.getElementById("table-result");
	 var comp=$.trim($("#id").val());
	console.log("参数data:"+comp);
	$.ajax({
		"url":url,
	  "data":{
			"comp":comp,
		},  
		"type":"post",
		"dataType":"json",
		"success":function(json){
			if(json.state==200){
				console.log("查询成功！");
				var data=json.data;
				console.log("查询成功！"+json.data);
				var str="";
				$("#company").text(str);
				str+="<tr class='text-c'>"+
				"<td align='center'>"+data.id+"</td>"+
				"<td align='center'>"+data.companyId+"</td>"+
				"<td align='center'><a target='_blank' style='color: blue;'   href='/index5?companyId="+data.id+"'>"+data.branchName+"</a></td>"+
				"<td align='center'>"+data.address+"</td>"+
				"<td align='center'>"+(data.content==null?'':data.content)+"</td>"+
				"<td align='center'>"+data.phone+"</td>"+
				"</tr>";
				tbody.innerHTML=str;
			}else if(json.state==402){
				alert(json.message);
			}
		}
	});
}



