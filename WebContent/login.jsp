<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  %>
<!DOCTYPE html>

<html  class="no-js" xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="zh-CN" >

    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>登陆</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->

        <link rel="stylesheet" href="${pageContext.request.contextPath}/front/login/placeholder_login.css">


        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>


        
<form id="slick-login" action="login.do" method="post">
<label for="username">username</label><input type="text" name="username" class="placeholder" placeholder="用户名">
<label for="password">password</label><input type="password" name="password" class="placeholder" placeholder="密码">
<input type="submit" value="登录">
</form>
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
 
        <script src="${pageContext.request.contextPath}/front/login/placeholder_login.js"></script>

    </body>

</html>