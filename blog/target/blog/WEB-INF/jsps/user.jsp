<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-13
  Time: 下午3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .blogTable {
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
<h1 style="text-align: center">个人信息</h1>
<table align="center" style="text-align: center">
    <tr>
        <td>
            用户名：
        </td>
        <td>
            ${sessionScope.user.username}
        </td>

    </tr>
    <tr>
        <td>
            性别：
        </td>
        <td>
            <c:choose>
                <c:when test="${sessionScope.user.sex == 0}">
                    女
                </c:when>
                <c:when test="${sessionScope.user.sex == 1}">
                    男
                </c:when>
            </c:choose>
        </td>

    </tr>
    <tr>
        <td>
            手机号：
        </td>
        <td>
            ${sessionScope.user.telephone}
        </td>
    </tr>
    <tr>
        <td align="center">
            <button onclick="location.href='/user?action=change'">修改个人信息</button>
        </td>
        <td align="center">
            <button onclick="location.href='/home'">返回主页</button>
        </td>
        <td align="center">
            <button onclick="location.href='/user?action=quit'">退出</button>
        </td>
    </tr>
</table>
<h2 align="center">我的博文</h2>
<div align="center">
    <a href="/article?action=add">新建博文</a>
</div>
<div align="center" style="margin-top: 20px">
    <table style="text-align: center; border : solid">
        <tr>
            <td class="blogTable">文章编号</td>
            <td class="blogTable">文章标题</td>
            <td class="blogTable">创建时间</td>
            <td class="blogTable">作者</td>
        </tr>
        <c:forEach var="myArticle" items="${sessionScope.myArticles}">
            <tr id="${myArticle.id}" onclick="go(this)" onmouseover="this.style.cursor='hand'"
                onmouseout="this.style.cursor='normal'">
                <td class="blogTable">${myArticle.id}</td>
                <td class="blogTable">${myArticle.title}</td>
                <td class="blogTable">${myArticle.date}</td>
                <td class="blogTable">${myArticle.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
