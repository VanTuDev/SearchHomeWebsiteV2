<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="metaClient.jsp"/>
    <title>Đăng ký</title>
</head>
<body>
<section class="section-content" style="margin: 100px 0;">
    <div class="card mx-auto" style="max-width: 380px">
        <div class="card-body">
            <c:if test="${not empty requestScope.successMessage}">
                <div class="alert alert-success" role="alert">${requestScope.successMessage}</div>
            </c:if>
            <c:if test="${not empty requestScope.errorMessage}">
                <div class="alert alert-danger" role="alert">${requestScope.errorMessage}</div>
            </c:if>

            <h4 class="card-title mb-4">Đăng ký</h4>
            <form action="${pageContext.request.contextPath}/signup" method="post">
                <div class="mb-3">
                    <label for="inputUsername" class="form-label">Tên đăng nhập</label>
                    <input type="text"
                           class="form-control ${not empty requestScope.violations.usernameViolations
                   ? 'is-invalid' : (not empty requestScope.values.username ? 'is-valid' : '')}"
                           id="inputUsername"
                           name="username"
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
                    <label for="inputPassword" class="form-label">Mật khẩu</label>
                    <input type="password"
                           class="form-control ${not empty requestScope.violations.passwordViolations
                   ? 'is-invalid' : (not empty requestScope.values.password ? 'is-valid' : '')}"
                           id="inputPassword"
                           name="password"
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
                <div class="mb-3">
                    <label for="inputFullname" class="form-label">Họ và tên</label>
                    <input type="text"
                           class="form-control ${not empty requestScope.violations.fullnameViolations
                   ? 'is-invalid' : (not empty requestScope.values.fullname ? 'is-valid' : '')}"
                           id="inputFullname"
                           name="fullname"
                           value="${requestScope.values.fullname}">
                    <c:if test="${not empty requestScope.violations.fullnameViolations}">
                        <div class="invalid-feedback">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.fullnameViolations}">
                                    <li>${violation}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <div class="form-check d-inline-block me-4">
                        <input class="form-check-input ${not empty requestScope.violations.genderViolations
                     ? 'is-invalid' : (not empty requestScope.values.gender ? 'is-valid' : '')}"
                               type="radio"
                               name="gender"
                               id="radioGender1"
                               value="0"
                        ${requestScope.values.gender.equals("0") ? 'checked' : ''}>
                        <label class="form-check-label" for="radioGender1">Nam</label>
                    </div>
                    <div class="form-check d-inline-block">
                        <input class="form-check-input ${not empty requestScope.violations.genderViolations
                     ? 'is-invalid' : (not empty requestScope.values.gender ? 'is-valid' : '')}"
                               type="radio"
                               name="gender"
                               id="radioGender2"
                               value="1"
                        ${requestScope.values.gender.equals("1") ? 'checked' : ''}>
                        <label class="form-check-label" for="radioGender2">Nữ</label>
                    </div>
                    <div class="mb-3">
                        <label for="inputPhoneNumber" class="form-label">Số điện thoại</label>
                        <input type="text"
                               class="form-control ${not empty requestScope.violations.phoneNumberViolations
                   ? 'is-invalid' : (not empty requestScope.values.phoneNumber ? 'is-valid' : '')}"
                               id="inputPhoneNumber"
                               name="phoneNumber"
                               value="${requestScope.values.phoneNumber}">
                        <c:if test="${not empty requestScope.violations.phoneNumberViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.phoneNumberViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="email"
                               class="form-control ${not empty requestScope.violations.emailViolations
                   ? 'is-invalid' : (not empty requestScope.values.email ? 'is-valid' : '')}"
                               id="inputEmail"
                               name="email"
                               value="${requestScope.values.email}">
                        <c:if test="${not empty requestScope.violations.emailViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.emailViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="inputEmail" class="form-label">Căng cước công dân</label>
                        <input type="number"
                               class="form-control ${not empty requestScope.violations.cccdViolations
                   ? 'is-invalid' : (not empty requestScope.values.cccd ? 'is-valid' : '')}"
                               id="inputEmail"
                               name="cccd"
                               value="${requestScope.values.cccd}">
                        <c:if test="${not empty requestScope.violations.cccdViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.cccdViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>



                </div>
                <div class="mb-3">
                    <label for="user-imageName" class="form-label">Thêm hình tài khoản</label>
                    <input type="file"
                           class="form-control"
                           id="user-imageName"
                           name="image"
                           accept="image/*">
                </div>
                <div class="mb-3 form-check">
                    <input class="form-check-input ${not empty requestScope.violations.policyViolations
                   ? 'is-invalid' : (not empty requestScope.values.policy ? 'is-valid' : '')}"
                           type="checkbox"
                           value="checked"
                           id="checkboxPolicy"
                           name="policy"
                           checked>
                    <label class="form-check-label" for="checkboxPolicy">
                        Đồng ý với <a href="#">điều khoản sử dụng</a>
                    </label>
                    <c:if test="${not empty requestScope.violations.policyViolations}">
                        <div class="invalid-feedback">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.policyViolations}">
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
                    Đăng ký Google
                </a>
                <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
            </form>
        </div> <!-- card-body.// -->
    </div> <!-- card.// -->
    <p class="text-center mt-4">Đã có tài khoản? <a href="${pageContext.request.contextPath}/signin">Đăng nhập</a></p>
</section> <!-- section-content.// -->

<%--<jsp:include page="_footer.jsp"/>--%>
</body>

</html>
