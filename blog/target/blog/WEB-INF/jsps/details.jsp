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
    <script type="text/javascript">
        function deleteCheck() {
            if (confirm("确定删除吗")){
                window.location.href="/article?action=delete&articleId=${article.id}";
            }
        }
    </script>
</head>
<body>
<div style="margin-top: 50px; text-align: center">
    <h1>${article.title}</h1>
    <div>
        <a href="/home">返回首页</a>
        <c:if test="${user.username eq article.username}">
        <a href="/article?action=change&articleId=${article.id}">编辑</a>
        <span onclick="deleteCheck()" style="cursor: pointer; text-decoration: underline; color: blue;">删除</span>
        </c:if>
    </div>
    <div style="border: solid;margin-left: 25%;margin-right: 25%; margin-top:20px;height: 200px">
        ${article.content}
    </div>
    <div style="margin-top: 20px">
    作者：${article.username}
    </div>
    <div>
    日期：${article.date}
    </div>
</div>
</body>
</html>
