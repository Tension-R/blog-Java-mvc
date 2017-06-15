<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-15
  Time: 上午9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">新建博文</h1>
<div align="center">
    <form action="/article?action=insert" method="post">
        <div>
            <label>标题：</label>
            <input type="text" name="title" required="required"/>
        </div>
        <div style="margin-top: 20px">
            <label>作者：</label>
            <label>${user.username}</label>
        </div>
        <div style="margin-top: 20px">
            <textarea name="content" required="required" style="height: 300px;width: 500px"></textarea>
        </div>
        <div style="margin-top: 20px">
            <input type="submit" value="保存">
            <input type="button" onclick="location.href='/home'" value="返回首页">
        </div>
    </form>
</div>
</body>
</html>
