<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-14
  Time: 下午8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1 align="center">查询页面</h1>

<c:if test="${userList != '[]' && userList != null}">
    <div align="center">
        <h2 align="center">查询到的用户</h2>
        <table style="text-align: center;">
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>用户名</td>
                    <td>${user.username}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<h2 align="center">查询到的博文</h2>
<div align="center">
    <table style="border: solid; text-align: center">
        <tr>
            <td>文章编号</td>
            <td>文章标题</td>
            <td>发表时间</td>
            <td>文章作者</td>
        </tr>
        <c:forEach var="article" items="${articleList}">
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
