<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
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
                    <c:when test="${not empty sessionScope.currentUser}">
                        <table border="1">
                            <tr>
                                <th>id</th>
                                <th>Bài đăng</th>
                                <th>Số tiền giao dịch</th>
                                <th>Mô tả</th>
                                <th>Thời gian</th>
                                <th>Trạng thái</th>
                            </tr>
                            <c:forEach var="payment" varStatus="loop" items="${requestScope.paymentDetails}">
                                <tr>
                                    <th scope="row">${loop.index + 1}</th>
                                    <td><a href="${pageContext.request.contextPath}/detail?id=${payment.postId}">Bài đăng chi tiết</a></td>
                                    <td>${payment.numberOfMoney}</td>
                                    <td>${payment.description}</td>
                                    <td>${payment.dateTime}</td>
                                    <td>${payment.status}</td>
                                </tr>
                            </c:forEach>

                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>
                            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng trang này.
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</section>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<%@include file="_footer.jsp" %>