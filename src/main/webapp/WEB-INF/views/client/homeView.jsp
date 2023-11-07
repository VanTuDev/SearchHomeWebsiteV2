<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="metaClient.jsp"/>
    <title>Search House</title>
</head>

<body>
<%@include file="_header.jsp" %>
<%--<%@include file="detailSort.jsp" %>--%>
<%@include file="content.jsp" %>
<%@include file="_footer.jsp" %>
<!-- Bootstrap Bundle với Popper -->
<a href="${pageContext.request.contextPath}/admin">Day la trang admin</a>

</body>
</html>