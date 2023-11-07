<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="contextPath" content="${pageContext.request.contextPath}"/>
<c:if test="${not empty sessionScope.currentUser}">
    <meta name="currentUserId" content="${sessionScope.currentUser.id}"/>
</c:if>
<c:if test="${not empty sessionScope.currentUser}">
    <meta name="currentUserId" content="${sessionScope.currentUser.email}"/>
</c:if>
<%--<c:if test="${not empty requestScope.post}">--%>
<%--&lt;%&ndash;    <meta name="postId" content="${requestScope.post.id}"/>&ndash;%&gt;--%>
<%--</c:if>--%>
<link href="${pageContext.request.contextPath}/img/favicon.png" rel="shortcut icon" type="image/x-icon">
<link href="${pageContext.request.contextPath}/img/addPhotoCamera.png" rel="shortcut icon" type="image/x-icon">

<!-- Bootstrap v5.0.1 -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/header.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">


<!-- Bootstrap Icons v1.5.0 -->
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" type="text/css" rel="stylesheet">

<!-- Custom Styles -->
<link href="${pageContext.request.contextPath}/css/stylesCilent.css" type="text/css" rel="stylesheet">