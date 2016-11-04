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
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/supersized.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>登陆</h1>
            <form action="login.do" method="post">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="submit">登陆</button>
                <div class="error"><span>+</span></div>
            </form>
 
        </div>
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>

    </body>

</html>