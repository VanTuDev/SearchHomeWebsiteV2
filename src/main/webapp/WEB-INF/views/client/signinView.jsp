<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="metaClient.jsp"/>
    <title>Đăng nhập</title>
</head>

<body>
<%--<jsp:include page="_headerLog.jsp"/>--%>
<section class="section-content" style="margin: 100px 0;">
    <div class="card mx-auto" style="max-width: 380px">
        <div class="card-body">
            <h4 class="card-title mb-4">Đăng nhập</h4>
            <form action="${pageContext.request.contextPath}/signin" method="post">
                <div class="mb-3">
                    <input name="username"
                           class="form-control ${not empty requestScope.violations.usernameViolations
                   ? 'is-invalid' : (not empty requestScope.values.username ? 'is-valid' : '')}"
                           placeholder="Tên đăng nhập"
                           type="text"
                           autocomplete="off"
                           value="${requestScope.values.username}">
                    <c:if test="${not empty requestScope.violations.usernameViolations}">
                        <div class="invalid-feedback">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.usernameViolations}">
                                    <li>${violation}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <input name="password"
                           class="form-control ${not empty requestScope.violations.passwordViolations
                   ? 'is-invalid' : (not empty requestScope.values.password ? 'is-valid' : '')}"
                           placeholder="Mật khẩu"
                           type="password"
                           autocomplete="off"
                           value="${requestScope.values.password}">
                    <c:if test="${not empty requestScope.violations.passwordViolations}">
                        <div class="invalid-feedback">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.passwordViolations}">
                                    <li>${violation}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <a class="btn btn-primary w-100 mb-1"
                   href="https://accounts.google.com/o/oauth2/auth?client_id=1088499236693-j3p4cgo5gb3h8g0nhl3nifodifg14ke4.apps.googleusercontent.com
&redirect_uri=http://localhost:8080/Gradle___com_example___demo_1_0_SNAPSHOT_war/callback
&response_type=code&scope=email&prompt=select_account" >
                    Đăng Nhập  Google
                </a>
                <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                <br>


            </form>
        </div> <!-- card-body.// -->
    </div> <!-- card .// -->
    <p class="text-center mt-4">Không có tài khoản? <a href="${pageContext.request.contextPath}/signup">Đăng ký</a></p>
</section> <!-- section-content.// -->




</body>

</html>
