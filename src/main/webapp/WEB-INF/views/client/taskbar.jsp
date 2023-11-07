<%--
    Document   : taskbar
    Created on : Oct 20, 2023, 8:43:25 AM
    Author     : Alpha
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<div class="right_keyword">
    <br>
    <br>
    <br>
    <div class="title module_title"> <span>Danh mục cho thuê</span> </div>

    <ul class="list">
        <c:forEach items="${requestScope.roomTypes}" var="type">
            <li>
                <a href="${pageContext.request.contextPath}/type?id=${type.id}"> ${type.name}  </a>
            </li>
        </c:forEach>
    </ul>

</div>
<div class="right_keyword">
    <div class="title module_title"> <span>Khu vực cho thuê</span> </div>
    <ul class="list">
        <c:forEach items="${requestScope.address}" var="address">
            <li>
                <a href="${pageContext.request.contextPath}/district?id=${address.districtCode}">Quận ${address.districtName}  </a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>