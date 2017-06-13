<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-12
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        td {
            width: 200px;
            height: 50px;
        }

    </style>
    <script type="text/javascript">
        function go(tag) {
            window.open("/home?action=detail&articleId=" + tag.id);
        }

    </script>
</head>
<body>
<div style="text-align: center">
    <h1>欢迎来到tension博客网站</h1>
</div>
<div style="text-align: right; margin-right: 120px;" >
    <c:choose>
        <c:when test="${sessionScope.user.username != null}">
            欢迎： ${user.username}&nbsp;&nbsp;
            <a href="/user?action=logOn">个人中心</a>
            <span><a href="/user?action=quit">退出</a></span>
        </c:when>
        <c:when test="${sessionScope.user.username == null}">
            <a href="/home?action=login ">登录</a>
        </c:when>
    </c:choose>
</div>


<div style="text-align:center;">
    <form action="/home?action=query" method="post">
        <input type="text" name="title"  placeholder="标题">
        <input type="text" name="author" placeholder="作者">
        <input type="submit" value="查找">
    </form>
</div>
<div align="center">
    <table style="text-align: center; border: solid">
        <tr>
            <td>文章编号</td>
            <td>文章标题</td>
            <td>发表时间</td>
            <td>文章作者</td>
        </tr>
        <c:forEach var="article" items="${list}">
            <tr id="${article.id}" onclick="go(this)" onmouseover="this.style.cursor='hand'"
                onmouseout="this.style.cursor='normal'">
                <td>${article.id}</td>
                <td>${article.title}</td>
                <td>${article.date}</td>
                <td>${article.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
