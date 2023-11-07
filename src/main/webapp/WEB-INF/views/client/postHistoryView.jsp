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
                    <c:when test="${not empty sessionScope.currentUser}">
                        <table border="1">
                            <tr>
                                <th>id</th>
                                <th>Tên bài đăng</th>
                                <th>Chi tiết</th>
                                <th>Thời gian</th>
                                <th>Trạng thái</th>
                                <th>Thao tác</th>
                            </tr>
                            <c:forEach var="post" varStatus="loop" items="${requestScope.post}">
                                <tr>
                                    <th scope="row">${loop.index + 1}</th>
                                    <td><a href="${pageContext.request.contextPath}/detail?id=${post.id}">${post.title}</a></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${post.status == 1}">
                                                Hiện ---
                                            </c:when>
                                            <c:otherwise>
                                                Ẩn --- Giao dịch thành công ! Vui lòng gia hạn lại bài đăng !
                                            </c:otherwise>
                                        </c:choose>

                                    </td>

                                    <td>${post.descriptions}</td>
                                    <td>${post.createAt}</td>
                                    <td><a href="${pageContext.request.contextPath}/updatePostFromUser?id=${post.id}">Sửa </a></td>
                                    <td>
                                        <button type="submit"
                                                class="btn ${post.status == 1 ? 'btn-success' : 'btn-secondary'}"
                                                form="update-show-${post.id}"
                                                onclick="return confirm('Bạn có muốn mở lại bài này?')">
                                            Hiện
                                        </button>
                                        <form action="${pageContext.request.contextPath}/updateStatus" method="post"
                                              id="update-show-${post.id}">
                                            <input type="hidden" name="id" value="${post.id}">
                                            <input type="hidden" name="action" value="SHOW">
                                        </form>
                                        <button type="submit"
                                                class="btn ${post.status == 0 ? 'btn-success' :'btn-secondary' }"
                                                form="update-hide-${post.id}"
                                                onclick="return confirm('Bạn có muốn ẩn này?')">
                                            Ẩn
                                        </button>
                                        <form action="${pageContext.request.contextPath}/updateStatus" method="post"
                                              id="update-hide-${post.id}">
                                            <input type="hidden" name="id" value="${post.id}">
                                            <input type="hidden" name="action" value="HIDE">
                                        </form>

                                    </td>
                                    <form action="updateStatus" method="post">

                                    </form>

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