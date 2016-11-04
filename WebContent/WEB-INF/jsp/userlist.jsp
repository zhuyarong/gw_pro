<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.jl.page.*,com.jl.mybean.*" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8"/>
	<title>用户列表</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/front/list/css/layout.css" type="text/css" media="screen" />

</head>
<body>
<header id="header">
		<hgroup>
			<h1 class="site_title"><a href="index.html">畜牧云平台管理</a></h1>
			<h2 class="section_title">用户信息</h2><div class="btn_view_site"><a href="http://localhost:8090/testmvc/index.do?num=8090">退出</a></div>
		</hgroup>
	</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
			<p>管理员 </p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs"><a href="#" id="currentMeun">用户资源管理</a> <div class="breadcrumb_divider"></div> <a class="current"href="#" id="currentSubmeun">用户信息</a></article>
		</div>
	</section><!-- end of secondary bar -->
	
	<aside id="sidebar" class="column">
		
		<hr/>
		<h3><span>用户资源管理</span></h3>
		<ul class="toggle">
			<li class="icn_categories"><a href="#">用户信息总览</a></li>
			<li class="icn_new_article"><a href="#">用户信息</a></li>
			<li class="icn_profile"><a href="#">管理员信息</a></li>
		</ul>
		<h3><span>用户信息修改</span></h3>
		<ul class="toggle">
			<li class="icn_add_user"><a href="#">新增用户</a></li>
		</ul>
		<h3><span>用户设置</span></h3>
		<ul class="toggle">
			<li class="icn_security"><a href="#">密码修改</a></li>
			<li class="icn_jump_back"><a href="#">退出</a></li>
		</ul>
		
		<footer>
			<hr />
			<!--
			<p><strong>Copyright &copy; 2016 Website Admin</strong></p>
			<p>Theme by <a href="#">MediaLoot</a></p>
			_-->
		</footer>
	</aside><!-- end of sidebar -->
	
	<section id="main" class="column">
		
		<h4 class="alert_info">你好，${username}</h4>
		
		<article class="module width_full">
			<header><h3>统计</h3></header>
			<div class="module_content">
				<article class="stats_graph">
					<div class="overview_today">
						<p class="overview_day">用户</p>
						<p class="overview_count">1,876</p>
			
					</div>
					<div class="overview_previous">
						<p class="overview_day">牛羊数量</p>
						<p class="overview_count">1,6246</p>
			
					</div>
				</article>
				
				<article class="stats_overview">
					<div class="overview_today">
						<p class="overview_day">今日新增牛</p>
						<p class="overview_count">${todayCustoms}</p>
			
					</div>
					<div class="overview_previous">
						<p class="overview_day">今日新增羊</p>
						<p class="overview_count">1,646</p>
			
					</div>
				</article>
				<div class="clear"></div>
			</div>
		</article><!-- end of stats article -->
		
		<article class="module width_3_quarter">
		
		<header><h3 class="tabs_involved">用户信息</h3>
		
		<ul class="tabs">
			<li><span  class="quick_search"><input type="text" value="请输入用户姓名" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;"></span></li>
   			<li><a href="#">查询</a></li>
    		
		</ul>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   					<th></th> 
    				<th>姓名</th> 
    				<th>手机号</th> 
    				<th>创建时间</th> 
    				<th>操作</th> 
				</tr> 
			</thead> 
			<tbody id="pageInfo"> 
	<%
 		Page<Users> users=null; 
 	 		users =(Page)request.getAttribute("users");
 	%><% 
	for(Users user:users.getCurruentPage()){
		%>
		<tr > 
   					<td><input type="checkbox"></td> 
    				<td><%=user.getUsername() %></td> 
    				<td><%=user.getTel() %></td> 
    				<td><%=user.getEmail()%></td> 
    				<td><input type="image" src="front/list/images/icn_edit.png" title="修改" class="updateUser">
					<input class="delUser"  type="image" src="front/list/images/icn_trash.png" title="删除"></td> 
				</tr> 
		
		<% 
		
	}
	%>


	
				   
			</tbody> 
						<tr> 
   					<td colspan="5">
					 <div class="tcdPageCode pagea">
					</div>
					</td> 
				</tr> 
			</table>
			</div><!-- end of #tab1 -->

			
		</div><!-- end of .tab_container -->
		
		</article><!-- end of content manager article -->
		
		<article class="module width_quarter">
			<header><h3>用户<span>a</span>拥有牛羊详情</h3></header>
			<div class="message_list">
				<div class="module_content">
			
					<div class="message">
						<p><strong>羊1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
					<div class="message">
						<p><strong>牛1</strong></p>
						<p><span>编号:</span>13524245052</p>
						<p><span>注册时间:</span>2016-03-12 08:30:29</p>
					</div>
				</div>
			</div>
			
		</article><!-- end of messages article -->
		
	
		
		
	<!--	
		<h4 class="alert_warning">A Warning Alert</h4>
		
		<h4 class="alert_error">An Error Message</h4>
		
		<h4 class="alert_success">A Success Message</h4>
		-->

		<div class="spacer"></div>
	</section>

	<!--script src="js/jquery-1.5.2.min.js" type="text/javascript"></script-->
	<script src="${pageContext.request.contextPath}/front/list/js/jquery-1.12.4.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/front/list/js/hideshow.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/front/list/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/list/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/list/js/jquery.page.js"></script>
	<script type="text/javascript">

$(document).ready(function() {
	/**function getPages(pagenum) {  
	    $.ajax( {  
	        type : "get",  
	        url : "getPage1.do",
	        data:{nextPageNum:pagenum,pageId:""},
	        dataType:"json",  
	        success : function(msg) {  
	          alert(msg.result);
	        },
	        fail:function(msg) {  
	          alert("2222");
	        },
	    });  
	}  	
	**/
	function getPages(pagenum) {  
	    $.ajax( {  
	        type : "get",  
	        url : "getPage1.do",
	        dataType:"json",  
	        success : function(data) {  
	        	$("#pageInfo").html("");
			for(var i=0;i<data.list.length;i++){
				var html="<tr>"
		        	+"<td><input type='checkbox'></td>"
		        	+"<td>"+(data.list[i]).username+"</td>"
		        	+"<td>"+(data.list[i]).phone+"</td> "
		        	+"<td>"+(data.list[i]).email+"</td>"
		        	+"<td><input type=\"image\" src=\"front/list/images/icn_edit.png\" title=\"修改\" class=\"updateUser\">"
		        	+"<input class=\"delUser\"  type=\"image\" src=\"front/list/images/icn_trash.png\" title=\"删除\"></td> "
		        	+"</tr> ";
		        	$("#pageInfo").append(html);
			}
			/**
	        	var html="<tr>"
	        	+"<td><input type='checkbox'></td>"
	        	+"<td>"+(data.list[3]).username+"</td>"
	        	+"<td>"+(data.list[3]).phone+"</td> "
	        	+"<td>"+(data.list[3]).email+"</td>"
	        	+"<td><input type=\"image\" src=\"front/list/images/icn_edit.png\" title=\"修改\" class=\"updateUser\">"
	        	+"<input class=\"delUser\"  type=\"image\" src=\"front/list/images/icn_trash.png\" title=\"删除\"></td> "
	        	+"</tr> ";
	        	$("#pageInfo").html(html);
	        	**/
	          //alert((data.list[3]).username);
	        },
	        fail:function(msg) {  
	          alert("error!");
	        },
	    });  
	}  	
$(".tcdPageCode").createPage({
        pageCount:<%=users.getPages()%>,
        current:<%=users.getCurruentPageNum()%>,
        backFn:function(p){
        	var uname="<%=request.getAttribute("username")%>";
        	var key="<%=users.getkey()%>";
        	//var url ="http://127.0.0.1:8080/xmserver/getPage.do?username="+uname+"&nextPageNum="+p+"&pageId="+key;
        	//window.location.href = url;
        	 
        	getPages(p);
            //单击回调方法，p是当前页码
        	/**
        	$.post("getPage.do", { username:"", nextPageNum:p,pageId:""} );
        	**/
        }
    });
$(".toggle>li>a").click(function(){
	$("#currentMeun").text($(this).parents("ul").prev().find("span").text());
	$("#currentSubmeun").text($(this).text());
});

$(".updateUser").click(function(){
alert($(this).parent().prev().prev().prev().text());
	
});
$(".delUser").click(function(){
alert($(this).parent().prev().prev().prev().text());
	
});

});
    </script>
    <script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
</script>
</body>
</html>