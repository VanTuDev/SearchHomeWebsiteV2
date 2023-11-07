<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="metaClient.jsp" %>
<%@include file="_header.jsp" %>
<br>
<br>
<br>
<br>
<section class="content mainContent">
    <div class="container content_cat">
        <div class="row">
            <div class="col-md-3 user_left">
                <div class="top text-center">
                    TÀI KHOẢN:<a class="name" href="#">${requestScope.user.fullname} </a>
                </div>
                <div class="title"><a href="${pageContext.request.contextPath}/client/settingView">Thông tin cá nhân</a></div>
                <div class="title"><a href="${pageContext.request.contextPath}/postHistory?id=${requestScope.user.id}">Danh sách tin rao bán</a></div>
                <div class="title"><a href="${pageContext.request.contextPath}/paymentHistory?id=">Lịch sử giao dịch</a></div>
                <div class="title"> <a href="${pageContext.request.contextPath}/client/updateToRenter">Nâng cấp</a></div>
            </div>

            <div class="col-md-9 user_right border rounded p-4">
                <c:choose>
                    <c:when test="${empty sessionScope.currentUser}">
                        <p>
                            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng chức năng thiết đặt.
                        </p>
                    </c:when>
                    <c:otherwise>
                        <div class="mt-4">
                            <c:if test="${not empty requestScope.successMessage}">
                                <div class="alert alert-success" role="alert">${requestScope.successMessage}</div>
                            </c:if>
                            <c:if test="${not empty requestScope.errorMessage}">
                                <div class="alert alert-danger" role="alert">${requestScope.errorMessage}</div>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-7">
                                <form action="${pageContext.request.contextPath}/client/updateToRenter" method="post">
                                    <div class="mb-3">
                                        <label for="inputUsername" class="form-label custom-input-label">Tên đăng nhập</label>
                                        <input type="text" class="form-control" id="inputUsername" name="username" value="${requestScope.user.username}">
                                    </div>
                                    <div class="mb-3">
                                        <label for "inputFullname" class="form-label custom-input-label">Họ và tên</label>
                                        <input type="text" class="form-control" id="inputFullname" name="fullname" value="${requestScope.user.fullname}">
                                    </div>

                                    <div class="mb-3">
                                        <label for="inputEmail" class="form-label custom-input-label">Email</label>
                                        <input type="email" class="form-control" id="inputEmail" name="email" value="${requestScope.user.email}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="inputPhoneNumber" class="form-label custom-input-label">Số điện thoại</label>
                                        <input type="text" class="form-control" id="inputPhoneNumber" name="phoneNumber" value="${requestScope.user.phoneNumber}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="custom-input-label">Giới tính</label>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="radioGender1" value="0" ${requestScope.user.gender == 0 ? 'checked' : ''}>
                                            <label class="form-check-label custom-radio-label" for="radioGender1">Nam</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="radioGender2" value="1" ${requestScope.user.gender == 1 ? 'checked' : ''}>
                                            <label class="form-check-label custom-radio-label" for="radioGender2">Nữ</label>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="inputAddress" class="form-label custom-input-label">CCCD</label>
                                        <input type="text" class="form-control" id="inputAddress" name="cccd" value="${requestScope.user.cccd}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="custom-input-label">Tài khoản ngân hàng <span style="color: red">(*)</span></label>
                                        <input type="text" class="form-control" name="bankAccount" value="${requestScope.user.bankAccount}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="custom-input-label">Tên ngân hàng <span style="color: red">(*)</span></label>
                                        <input type="text" class="form-control" name="nameBankAccount" value="${requestScope.user.nameBankAccount}">
                                    </div>
                                    <button type="submit" class="btn btn-primary w-100">Cập nhật thông tin mới</button>
                                </form>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<%@include file="_footer.jsp" %>