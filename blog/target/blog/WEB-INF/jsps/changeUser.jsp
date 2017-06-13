<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tension
  Date: 17-6-13
  Time: 下午4:56
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
<body onload="checkRepeat()">
<h1 style="text-align: center">修改${sessionScope.user.username}的个人信息</h1>
<form action="/user?action=update" method="post">
    <table align="center">
        <tr>
            <td>
                用户名：
            </td>
            <td>
                <input type="text" name="username" placeholder="${sessionScope.user.username}"/>
            </td>
        </tr>
        <tr>
            <td>
                性别：
            </td>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.user.sex == 1}">
                        <label><input type="radio" name="sex" value="1" checked="checked">男</label>
                        <label><input type="radio" name="sex" value="0">女</label>
                    </c:when>
                    <c:when test="${sessionScope.user.sex == 0}">
                        <label><input type="radio" name="sex" value="1">男</label>
                        <label><input type="radio" name="sex" value="0" checked="checked">女</label>
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                手机号：
            </td>
            <td>
                <input type="text" name="telephone" placeholder="${sessionScope.user.telephone}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="修改"/>
                <button type="button" onclick="location.href='/home'">返回主页</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
