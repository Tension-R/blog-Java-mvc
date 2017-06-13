<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-12
  Time: 下午1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        /**
         * 标记用户名密码是否匹配
         */
        function check() {
            var log = ${log};
            if (!log){
                alert("用户名或密码错误");
            }
        }
    </script>
</head>
<body onload="check()">
<h1 style="text-align: center">用户登录</h1>
<form action="/user?action=logOn" method="post">
    <table align="center">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" placeholder="用户名" required="required"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" placeholder="密码" required="required"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="登录"/>
                <button type="button" onclick="location.href='/user?action=register'">注册</button>
            </td>
        </tr>
    </table>
</form>
<div>

</div>
</body>
</html>
