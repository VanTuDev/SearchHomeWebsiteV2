<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="../_meta.jsp"/>
    <title>Trang chủ Admin</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content padding-y">
    <div class="container">
        <div class="card bg-light">
            <div class="card-body p-5">
                <h1 class="display-5 mb-5">Quản lý hệ thống</h1>
                <div class="row">
                    <div class="col-6 col-lg-3">
                        <figure class="card">
                            <div class="p-3">
                                <h4 class="title">${requestScope.totalUsers}</h4>
                                <span>người dùng</span>
                            </div>
                        </figure>
                        <figure class="card">
                            <div class="p-3">
                                <h4 class="title">${requestScope.totalPosts}</h4>
                                <span>Bài đăng</span>
                            </div>
                        </figure>
                    </div>
                </div>
            </div>
        </div> <!-- card.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<a href="${pageContext.request.contextPath}/admin?valid=101">Chưa xác thực</a>/<a href="${pageContext.request.contextPath}/admin?valid=202">Đã duyệt</a>/<a href="${pageContext.request.contextPath}/admin?valid=404">Không duyệt</a>

<c:if test="${not empty sessionScope.successMessage}">
<div class="alert alert-success mb-0 mt-4" role="alert">
        ${sessionScope.successMessage}
</div>
</c:if>
<c:if test="${not empty sessionScope.errorMessage}">
<div class="alert alert-danger mb-0 mt-4" role="alert">
        ${sessionScope.errorMessage}
</div>
</c:if>
<c:remove var="successMessage" scope="session"/>
<c:remove var="errorMessage" scope="session"/>
<main class="table-responsive-xl mb-5">
    <table class="table table-bordered table-striped table-hover align-middle">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">ID</th>
            <th scope="col">Tên đăng nhập</th>
            <th scope="col">Họ và tên</th>
            <th scope="col">SDT</th>
            <th scope="col">Số tài khoản</th>
            <th scope="col">Tên ngân hàng</th>
            <th scope="col">Tình trạng</th>
            <th scope="col">Thao tác</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" varStatus="loop" items="${requestScope.users}">
            <tr>
                <th scope="row">${loop.index + 1}</th>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.fullname}</td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.bankAccount}</td>
                <td>${user.nameBankAccount}</td>
                <td class="text-center text-nowrap">
                    <a class="btn btn-primary me-2"
                       href="${pageContext.request.contextPath}/admin/userManager/detail?id=${user.id}"
                       role="button">
                        Xem
                    </a>
                    <button type="submit"
                            class="btn ${user.valid == 202 ? 'btn-secondary' : 'btn-success'}"
                            form="update-show-${user.id}"
                            onclick="return confirm('Bạn có muốn xác thực người dùng này?')">
                        Duyệt
                    </button>
                    <form action="${pageContext.request.contextPath}/admin/userPreview" method="post"
                          id="update-show-${user.id}">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="action" value="SHOW">
                    </form>
                    <button type="submit"
                            class="btn ${user.valid == 404 ? 'btn-secondary' : 'btn-success'}"
                            form="update-hide-${user.id}"
                            onclick="return confirm('Bạn có muốn không xác thực người dùng này?')">
                        Huỷ
                    </button>
                    <form action="${pageContext.request.contextPath}/admin/userPreview" method="post"
                          id="update-hide-${user.id}">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="action" value="HIDE">
                    </form>


                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

