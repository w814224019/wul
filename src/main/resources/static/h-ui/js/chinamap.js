
var inpid=0;
var prpr;
var prprcolor;
$(document).ready(function(){
	$(".tg1_item img").css("width","100%");
	/*more-begin*/
	/*  $(".tuiguang1 .hd").append("<a href='#' class='hd_a'>更多>></a>");
    $(".baojia .hd").append("<a href='#' class='hd_a'>更多>></a>")
   
   $(".hd_a:eq(0)").attr('href','http://www.56114.net.cn/zx/6_111_l2.html');
    $(".hd_a:eq(1)").attr('href','http://www.56114.net.cn/zx/6_111_l1.html');
    $(".hd_a:eq(2)").attr('href','http://huoyun.56114.net.cn/yj/6_111.html');*/
    $(".tuiguang1:eq(0) .hd div:eq(0)").css('width','auto');
    $(".tuiguang1:eq(1) .hd div:eq(0)").css('width','auto');
    $(".baojia .hd div:eq(0)").css('width','auto');
	/*more-end*/
	
	 $("body").on('click','.more-a',function(){
		$(this).siblings("dl").toggleClass("arrow1");
		$(this).hide();
	});
	
	  $("body").on('mouseenter','.list-memu dd',function(){
		  $(this).css("background-color","#4082e6");
		  $(this).children(".list-one").css("color","#fff");
		   $(this).children("p").children("a").css("color","#fff");
		   prpr=$(this).children(".list-one").attr("id2");
		  prprcolor= $("path[id2="+prpr+"]").attr("fill");
		  $("path[id2="+prpr+"]").attr("fill","yellow");
		  });
	  $("body").on('mouseleave','.list-memu dd',function(){
		   $(this).css("background-color","#fff");
		  $(this).children(".list-one").css("color","#df0000");
		   $(this).children("p").children("a").css("color","#666");
		    $("path[id2="+prpr+"]").attr("fill",prprcolor);
		  });
    var provincecolor1;
    var provinceid1; 
	var provincecolor2;
    var provinceid2; 
	var ptxt;
   /* $("body").on('mouseenter','.xian',function(){
	 provinceid2= $(this).attr("id2");
	 ptxt=$("body").find($("path[id2="+provinceid2+"]")).length;

	if(ptxt!='0'){
       provincecolor2= $("path[id2="+provinceid2+"]").attr("fill");
       $("path[id2="+provinceid2+"]").attr("fill","yellow");
	}else{
       provinceid1=$(this).parent().siblings(".list-one").attr("id2");
       provincecolor1= $("path[id2="+provinceid1+"]").attr("fill");
       $("path[id2="+provinceid1+"]").attr("fill","yellow");
				}

				
      }); 
    
      $("body").on('mouseleave','.xian',function(){
		  if(ptxt!='0'){
			   $("path[id2="+provinceid2+"]").attr("fill",provincecolor2);
		  }else{
			   $("path[id2="+provinceid1+"]").attr("fill",provincecolor1);
				  }
    });*/
        

    var provincecolor;
    var provinceid; 
	
	
      $("body").on('mouseenter',"#WTMap_Main path,#WTMap_Main span",function(){
		  
         provinceid= $(this).attr("id2");
		 pclass=$("body").find($(".xian[id2="+provinceid+"]")).length;
		
		 if(pclass!="1"){
	          $("a[id2="+provinceid+"]").parent("dd").css("background-color","#4082e6");
			  $("a[id2="+provinceid+"]").css("color","#fff");
		      $("a[id2="+provinceid+"]").siblings("p").children().css("color","#fff");
			 }
		
       provincecolor= $("path[id2="+provinceid+"]").attr("fill");
       $("path[id2="+provinceid+"]").attr("fill","yellow");

      }); 
    
      $("body").on('mouseleave',"#WTMap_Main path,#WTMap_Main span",function(){
      $("path[id2="+provinceid+"]").attr("fill",provincecolor);
		 if(pclass!="1"){
	   $("a[id2="+provinceid+"]").parent("dd").css("background-color","#fff");
	    $("a[id2="+provinceid+"]").css("color","#df0000");
		 $("a[id2="+provinceid+"]").siblings("p").children().css("color","#666");
		 }
    });
	
	
	
      var provinceids;
      var provincename;
	 $(".c-close").on('click',function(){
		 $(".m-bottom dl").removeClass("arrow1").addClass("arrow");
				$(".more-a").show();
	 	$("#china_map,.m-box").hide();
	 	$(".theme-popover-mask").hide();
	 })
	$(".zx_cfd_inp,.zx_ddd_inp,.inp_zx_ddd,.inp_zx_cfd").click(function(){

		cdid=$(this).attr("id");
		console.log(cdid)
		/*if(cdid=="cfd"){ 
			$("#cfdid").val("");
		}
		else if($(cdid=="ddd")){ 
			$("#dddid").val("");
		}
		else if($(cdid=="cfd1")){ 
			$("#dddid").val("");
		}
		else if($(cdid=="ddd1")){ 
			$("#dddid").val("");
		};*/
		$(".theme-popover,#zhens").hide();
			 
		var gjson="";
		gjson+="<div class='m-top'><h1>二、国际线路：</h1></div>";
		
		for( m=0;m<5;m++){
		gname=guojijson[m].zname;
		gjson+="<div class='m-bottom'>";
		gjson+="<span >"+gname+"</span>";
		gjson+="<dl class='arrow'>";
		gnamearr=guojijson[m].guojia.split("|");
			for (  w=0;w<gnamearr.length;w++){
				
		var xarr1=gnamearr[w].split("_");
		gjson+="<dd><a href='javascript:;' id2="+xarr1[1]+">"+xarr1[0]+"</a> </dd>";
		
			
			}
			gjson+="</dl>";
			gjson+="<a class='more-a' href='javascript:;'>更多>></a>"
			gjson+="</div>";
			}
		
		$(".m-box").html(gjson);
		
		if (navigator.appName === 'Microsoft Internet Explorer') { //判断是否是IE浏览器
		var DEFAULT_VERSION7 = 7.0;
		var DEFAULT_VERSION8 = 8.0;
		var ua = navigator.userAgent.toLowerCase();
		var isIE = ua.indexOf("msie")>-1;
		var safariVersion;
		if(isIE){
			safariVersion =  ua.match(/msie ([\d.]+)/)[1];
			}
			if(safariVersion <= DEFAULT_VERSION7 ){
				alert("版本太低，请选择其他浏览器！")
				}
		else if(safariVersion == DEFAULT_VERSION8 ){
	    $("#china_map img").show();
		$("#china_map label").hide();
		$(".warning").show();
 		$(".china-mapbox").html(cmap);
		inpid=$(this).attr("id");
		$(".theme-popover-mask").css("height",$(document).height()).show();
		$("#china_map,.m-box").show();
		}
        else{
			$(".china-mapbox").html(cmap);
		inpid=$(this).attr("id");
		$(".theme-popover-mask").css("height",$(document).height()).show();
		$("#china_map,.m-box").show();
			}
		}
		else{
		$(".china-mapbox").html(cmap);
		inpid=$(this).attr("id");
		$(".theme-popover-mask").css("height",$(document).height()).show();
		$("#china_map,.m-box").show();
		}
		});
	$(".close").click(function(){
			$("#china_map,.m-box").show();
		$(".theme-popover,#shi,#xian,#zhen").hide();
		});
	$('body').on('click', '.zhentitle_right', function() {
		$("#zhen").hide();
	});
	$('body').on('click', '#china_map span,#china_map path', function() {
		provinceids= $(this).attr("id2");
		$("#china_map,.m-box").hide();
        // small-map-begin
		for(var i=0;i<arr1.length;i++){
			if(arr1[i]==provinceids){
				$("#province_maps").html(arr[i]);
			}
		}

		// small-map-end
		 provincename= $("span[id2="+provinceids+"]").text()
         $("#txtWTMapSearch").val(provincename);
      
		cityid=$(this).attr("id");
		princeid=$(this).attr("id2");
		$(".prince-a").text($(this).attr("txt"));
		$(".prince-a").attr("id2",princeid);
	
		
		if (cityid=="closesheng"){
			$('#div_map_hw,.theme-popover-mask,.theme-popover,#china_map,.m-box').hide();
			}
		else if (cityid=="am" || cityid=="xg" || cityid=="tw" || cityid=="dy" || cityid=="ss"){
			$("#"+inpid).val($(this).attr("txt"));
			$("#"+inpid+"id").val($(this).attr("id2"));
			$('.theme-popover-mask,.theme-popover,#div_map_hw,#china_map,.m-box').hide();
		}
		else{
			$("#china_map,.m-box").hide();
			$(".theme-popover").css("display","block");
			$(".theme-poptit").css("display","block");
			var citystr="";
			for (i=0;i<31;i++){
				sname=cityjson[i].sname;
				spy=cityjson[i].spy;
				if (cityid==spy){
					
					snamearr=sname.split("_")
					citystr += "<div class='list-memu' id='list-memu'><dl>";
					for (j=0;j<cityjson[i].shi.length;j++){
						xnamearr=cityjson[i].shi[j].name.split("_");
						citystr+="<dd><a class='list-one' id2="+xnamearr[1]+">"+xnamearr[0]+"</a>";
						citystr+="<p class='ppp'>";
						xstr=cityjson[i].shi[j].xian;
						var xarr=xstr.split("|");
						for(var k=0;k<xarr.length-1;k++){
							xxnamearr=xarr[k].split("_");
							citystr+="<a id2="+xxnamearr[1]+" class='xian' href='javascript:;'>"+xxnamearr[0]+"</a>";
						}
						citystr+="</p>";
						citystr+="</dd>";
					}
					citystr+="</dl></div><div class='shifgx'></div><div class='shifgx1'></div>";
					i=30;
		
		
					if(cityid=="tj"|| cityid=="bj"||cityid=="sh"||cityid=="hi"||cityid=="cq"){
						$(".list-memu  dd").hide();
						$(".theme-popbod #shi").css("overflow","hidden");					
						$(".theme-popover").css("width","850px");
						$("#shi").css("margin-top","125px");
						}else{
							//$(".theme-popbod #shi").css("overflow","auto");	
							//$(".theme-popbod #shi").css("overflow-x","hidden");					
						$(".theme-popover").css("width","1250px");
						$("#shi").css("margin-top","0px");
							}
				}
				
				$("#shi").html(citystr);
				$("#shi").show();
				
	
			}
		}  
		
		$("#shi").append("<a  class='prince-b'></a>");
		$(".prince-b").text($(this).attr("txt"));
		$(".prince-b").attr("id2",princeid);
		if (cityid=="bj" ||cityid=="tj"||cityid=="cq"||cityid=="sh"||cityid=="hi"){$(".prince-b").hide();}
		
		
	});
	
	$('body').on('click', '#shi div dl dd  .xian', function() {
		
		zhenid=$(this).attr("id2");
		zhenname=$(this).text();

		var X = $(this).position().top;
		var Y = $(this).position().left;
		$("#zhen").css({"top":(X+30)+"px","left":(Y-170)+"px"});
		$("#zhen").show();
		var zhenstr="<div class='zhentitle'><div class='zhentitle_left'>请选择</div><a class='az'   id2="+zhenid+">"+zhenname+"</a><div class='zhentitle_right'>关闭</div></div>";
		zhenstr+="<div class='zhenlist'><a id2="+zhenid+" class='zhen' href='javascript:;'>"+zhenname+"</a>";
		for (j=1;j<zhenjson.length;j++){
			if (zhenid==zhenjson[j].xid){
				
			if(zhenjson[j].xz==""){
			$("#"+inpid).val($(this).text());
			$("#"+inpid+"id").val($(this).attr("id2"));
			$('.theme-popover-mask,.theme-popover,#div_map_hw,#china_map,.m-box,#zhen').hide();
				}
				else{
				xzarr=zhenjson[j].xz.split("|");
				for(var x=0;x<xzarr.length-1;x++){
					xznamearr=xzarr[x].split("_");
					zhenstr+="<a id2="+xznamearr[1]+" class='zhen' href='javascript:;'>"+xznamearr[0]+"</a>";
				}
				zhenstr+="</div>"
				$("#zhen").html(zhenstr);
				$(".zhen-z").text($(".zhenlist a:eq(0)").text());
				var zzz=$(".zhenlist a:eq(0)").attr("id2");
				$(".zhen-z").attr("id2",zzz);
				}
			}
		}
	});
	$('body').on('click', '#shi div dl dd .list-one,#zhen .zhen,.zhen-z,.prince-a,.prince-b,#province_maps span,#province_maps path,.m-bottom dl dd a,.az', function() {
		 $('html ,body').animate({scrollTop: 0},10);
				$(".m-bottom dl").removeClass("arrow1").addClass("arrow");
				$(".more-a").show();
		$(".theme-popover,#zhen,#shi,#china_map,.m-box,.theme-popover-mask").hide();
		var cityid=$(this).attr("id2");
		$("#"+inpid).val($(this).text());
		if($(this).text()==''){
			$("#"+inpid).val($(this).attr("txt"));
		}
		$("#"+inpid+"id").val(cityid);
		if (inpid=="ddd"){
			cfd=$("#cfdid").val();
			ddd=$("#dddid").val();
			chufa=$("#cfd").val();
			daoda=$("#ddd").val();
			$("#cfd").focus();
			if (cfd=="" || cfd=="undefined"){cfd=getquid(chufa);}
			if (ddd=="" || ddd=="undefined"){ddd=getquid(daoda);}
			if (cfd!='' && ddd!='' && cfd!=0 && ddd!=0){
				//window.location="http://www.56114.net.cn/zx/"+cfd+"_"+ddd+".html";
				}
			else{
		alert("请选择出发地和到达地！");
		return false;
		}
			}
	});
});

function zx(){
	cfd=$("#cfdid").val();
	ddd=$("#dddid").val();
	chufa=$("#cfd").val();
	daoda=$("#ddd").val();
	if (cfd=="" || cfd=="undefined"){cfd=getquid(chufa);}
	if (ddd=="" || ddd=="undefined"){ddd=getquid(daoda);}
	if (cfd!='' && ddd!='' && cfd!=0 && ddd!=0){
		//window.location="http://www.56114.net.cn/zx/"+cfd+"_"+ddd+".html";
		}
	else{
		alert("请选择出发地和到达地！");
		return false;
		}
	}

 
function getquid(cfd){
    var temp=0;
	cfd=escape(cfd);
	//$("#cs").html("http://www.56114.net.cn/inc/ajaxset.asp?action=getquid&city="+cfd+"");
    $.ajax({
		url:
			"http://qun.56114.net.cn/wxqun/getcityid.php?callback=a&action=getquid_jsonp&city="+cfd+""
			,
		// url : "http://www.56114.net.cn/inc/ajaxset.asp?action=getquid&city="+cfd+"",
        async: true,
        type : "GET",
		 dataType:'jsonp',
		 jsonp: "callback",
		 scriptCharset: 'utf-8',
        success : function(data) {
            temp=data.cfdid;
			console.log(data.cfdid);
			//$("#cs").html(temp);
        },
		 complete: function(data){
			//$("#cs").html("-1");
           // alert('complete');
        }
    });
	//if (temp>0){
	console.log("a");
   return temp
	//}
}
function addScriptTag(src) {
  var script = document.createElement('script');
  script.setAttribute("type","text/javascript");
  script.src = src;
 document.body.appendChild(script);
}
window.onload = function () {
  addScriptTag(
		  
		  "http://www.56114.net.cn/inc/ajaxset.asp?action=getquid&city="+cfd+""
		  );
}