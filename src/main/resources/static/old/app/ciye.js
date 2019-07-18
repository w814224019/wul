$('#from').bind('input propertychange',function(){
	//alert()
	let data = $('#from').val();
	if(data === '' || data === undefined){
	}else{
		$.ajax({
			url: '../area1',
		    type: 'get',
		    dataType: 'json',
		    data: {'name': data},
		    success: function(res){
		    	//alert(res.state)
		    	if(res.state == 200){
			    	let shuju = res['data']
				    if(shuju.length === 0 || shuju.length === undefined){
				    	//alert('没有数据')
				    }else {
				    	$('.shiji').find('option').remove();
				    	for (let f=0; f<shuju.length; f++){
				    		var city = "";
				    		city = "<option data-id='"+shuju[f]['areaid']+"' value='"+shuju[f]['fareaname']+'-'+shuju[f]['areaname']+"'></option>";
				    		$('.shiji').append(city);
				    		//console.log(city);
				    	}
				    	
				    }
		    	}else if(res.state == 400){
		    		
		    	}else{
		    		alert('输入错误')
		    	}
		    },
	    });
	}
	
	
});
$('#to').bind('input propertychange',function(){
	//alert()
	let data = $('#to').val();
	if(data === '' || data === undefined){
	}else{
		$.ajax({
			url: '../area1',
		    type: 'get',
		    dataType: 'json',
		    data: {'name': data},
		    success: function(res){
		    	//alert(res.state)
		    	if(res.state == 200){
			    	let shuju = res['data']
				    if(shuju.length === 0 || shuju.length === undefined){
				    	//alert('没有数据')
				    }else {
				    	$('.shij').find('option').remove();
				    	for (let f=0; f<shuju.length; f++){
				    		var city = "";
				    		city = "<option data-id='"+shuju[f]['areaid']+"' value='"+shuju[f]['fareaname']+'-'+shuju[f]['areaname']+"'></option>";
				    		$('.shij').append(city);
				    		//console.log(city);
				    	}
				    	
				    }
		    	}else if(res.state == 400){
		    		
		    	}else{
		    		alert('输入错误')
		    	}
		    },
	    });
	}
	
	
});
function inputSelect(){
	//alert()
	var input_select=$("#from").val();
	var	option_id=$(".shiji option").attr('data-id');
	$('#from').attr('txt1', option_id);
	
	console.log(input_select,option_id);
	
};
function inputSelects(){
	//alert()
	var input_select=$("#to").val();
	var	option_id=$(".shij option").attr('data-id');
	$('#to').attr('txt', option_id);
	
	console.log(input_select,option_id);
	
};

//根据出发地和目的地查询
function datacx(){
	var sta=$("#from").attr("txt1");
	var sta1=$("#from").val();
	var end=$("#to").attr("txt");
	var end1=$("#to").val();
	console.log(sta+"ss")
	if(sta==''){
		alert("请修改出发地：‘"+sta1+"’的地址！")
	}else if(end==''){
		alert("请修改到达地：‘"+end1+"’的地址！")
	}else{
	location.href="ciye?sta="+sta+"&end="+end+""
	$("#from").val("");
	$("#to").val("");
	}
	
}


//按下回车登录
document.onkeydown = function (e) {
  var theEvent = window.event || e;
  var code = theEvent.keyCode || theEvent.which;
  if (code== 13)
  {
$("#btn-skey").click();
  }
}

