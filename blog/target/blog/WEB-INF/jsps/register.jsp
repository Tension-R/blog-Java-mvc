<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-13
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function checkRepeat() {
            var repeat = ${repeat};
            if (repeat){
                alert("用户名已存在");
            }
        }
    </script>
</head>
<body onload="checkRepeat()" >
<h1 style="text-align: center;">注册用户</h1>
<form action="/user?action=add" method="post">
    <table align="center">
        <tr>
            <td>
                用户名：
            </td>
            <td>
                <input type="text" name="username" placeholder="用户名" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                密码：
            </td>
            <td>
                <input type="password" name="password" placeholder="密码" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                性别：
            </td>
            <td>
                <label><input type="radio" name="sex" value="1" checked="checked">男</label>
                <label><input type="radio" name="sex" value="0">女</label>
            </td>
        </tr>
        <tr>
            <td>
                手机号：
            </td>
            <td>
                <input type="text" name="telephone" placeholder="手机号" required="required"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="注册"/>
                <button type="button" onclick="location.href='/home'">返回主页</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
