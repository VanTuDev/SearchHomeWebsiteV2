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
                <div class="title"><a href="${pageContext.request.contextPath}/postHistory?id=${sessionScope.currentUser.id}">Danh sách tin rao bán</a></div>
                <div class="title"><a href="${pageContext.request.contextPath}/paymentHistory?id=${sessionScope.currentUser.id}">Lịch sử giao dịch</a></div>
                <div class="title"> <a href="${pageContext.request.contextPath}/client/updateToRenter">Nâng cấp</a></div>
              </div>

            <div class="col-md-9 user_right border rounded p-4">
                <h1 class="module_title">Thông tin cá nhân : <b>${requestScope.user.fullname}</b> </h1>
                <div class="content">
                    <div class="user_post_form">
                        <div class="usi_description"></div>
                        <div class="module_title">Liên hệ</div>
                        <div class="user_info_text">
                            <div class="mb-3">
                                <label>Email:</label> ${requestScope.user.email} <b></b>
                            </div>
                            <div class="mb-3">
                                <label>Họ và tên:</label> <b>${requestScope.user.fullname}</b>
                            </div>
                            <div class="mb-3">
                                <label>Số điện thoại:</label> <b>${requestScope.user.phoneNumber}</b>
                            </div>
                            <div class="mb-3">
                                <label>CCCD:</label> <b>${requestScope.user.cccd}</b>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<%@include file="_footer.jsp" %>