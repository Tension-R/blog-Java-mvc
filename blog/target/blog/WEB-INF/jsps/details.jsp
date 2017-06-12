<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-12
  Time: 下午7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
标题：${article.title}
<br/>
正文：${article.content}
<br/>
作者：${article.username}
<br/>
日期：${article.date}
</body>
</html>
