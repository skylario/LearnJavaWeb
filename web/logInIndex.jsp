<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 17-12-18
  Time: 上午9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body style="text-align: center">
<h1>XXXX网站</h1>
<br/>
<div style="text-align: right">
    <c:if test="${user!=null}">
        欢迎您！${user.nickName}.
        <a href=“${pageContext.request.contextPath}/LogOutServlet”>注销</a>
    </c:if>

    <c:if test="${user==null}">
        <a href="${pageContext.request.contextPath}/servlet/RegisterUIServlet">注册</a>
        <a href="${pageContext.request.contextPath}/servlet/LogInUIServlet">登录</a>

    </c:if>

</div>
</body>
</html>
