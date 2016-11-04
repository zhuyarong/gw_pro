<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String ctx = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctx+"/";
%>
<link href="${pageContext.request.contextPath}/WebContent/front/bootstrap/css/bootstrap.css" rel="stylesheet" />

<link href="${pageContext.request.contextPath}/WebContent/front/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/WebContent/front/js/jquery-1.12.4.js" type="text/javascript" ></script>
<script src="<%=ctx %>/WebContent/front/bootstrap/js/bootstrap.js" type="text/javascript"  ></script>

