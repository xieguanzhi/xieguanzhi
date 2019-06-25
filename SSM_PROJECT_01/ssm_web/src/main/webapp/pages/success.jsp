<%--
  Created by IntelliJ IDEA.
  User: 36035
  Date: 2019/6/23
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<c:forEach items="${msg}" var="i">
    ${i.departureTimeStr}
</c:forEach>
</body>
</html>
