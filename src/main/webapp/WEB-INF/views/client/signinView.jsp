<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="metaClient.jsp"/>
    <title>Đăng nhập</title>
</head>

<body>
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
                <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                <br>
                <h1>Login with Google</h1>
                <a> Quên mật khẩu  </a>
                <a href="https://accounts.google.com/o/oauth2/auth?client_id=1088499236693-v50rr2v1k9p3t17khkcum2og4l0g3a3v.apps.googleusercontent.com&redirect_uri=http://localhost:8080/demo1_war_exploded/callback&response_type=code&scope=openid%20email&access_type=online&approval_prompt=auto">
                    <img src="google-login-button.png" alt="Login with Google">
                </a>

            </form>
        </div> <!-- card-body.// -->
    </div> <!-- card .// -->
    <p class="text-center mt-4">Không có tài khoản? <a href="${pageContext.request.contextPath}/signup">Đăng ký</a></p>
</section> <!-- section-content.// -->




</body>

</html>
