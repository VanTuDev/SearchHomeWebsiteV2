<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <style>
        .fas{
            color:var(--icon-color) !important;
        }
    </style>
</head>
<body>
<div>
    <div>
        <main class="table-responsive-xl mb-5">
            <table class="table table-bordered table-striped table-hover align-middle">

                <c:forEach var="posts" varStatus="loop" items="${requestScope.posts}">
                    <div class="list_pro">
                        <div class="pro_item row">
                            <div class="col-3">
                                <c:choose>
                                    <c:when test="${empty posts.imageName}">
                                        <a class="img" href="${pageContext.request.contextPath}/detail?id=${posts.id}">
                                            <img alt="${posts.imageName}"
                                                 src="${pageContext.request.contextPath}/img/50px.png" />
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="img" href="${pageContext.request.contextPath}/detail?id=${posts.id}">
                                            <img alt="${posts.imageName}"
                                                 src="${pageContext.request.contextPath}/image/${posts.imageName}" />
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="info col-9">
                                <div class="row">
                                    <h5 class="col-9">
                                        <a href="${pageContext.request.contextPath}/detail?id=${posts.id}">
                                            <strong style="color:#1A3560">${posts.title}</strong>
                                        </a>
                                    </h5>
                                    <p style="display:flex;justify-content: flex-end;" class="money info3 col-3 text-danger">
                                        <span class="info2_lable3"><strong>${posts.price}&#8363;</strong></span>
                                    </p>
                                </div>
                                <div class="group2">
                                    <p class="info_adrr">
                                        <span class="info2_lable"><i class="fas fa-map-marker"></i></span>
                                        <span class="info2_lable3"> ${posts.addressDetail}, ${posts.wardName},${posts.districtName},${posts.provinceName} </p>
                                </div>
                                <div class="intro"> ${posts.descriptions} </div>
                                <div class="group2 row">
                                    <p class="info3 col-3"> <span class="info2_lable"><strong><i
                                            class="fas fa-chart-area"></i></strong></span>
                                        <span class="info2_lable3">${posts.area}&#178;</span> </p>
                                    <p class="info3 col-3"> <span class="info2_lable"><strong><i class="fas fa-map"></i></strong></span>
                                        <span class="info2_lable3">${posts.districtName}</span> </p>
                                    <p class="info4 col-3"> <span class="info2_lable"><strong><i
                                            class="fas fa-calendar-check"></i></strong></span>
                                        <span class="time">${posts.createAt}</span> </p>
                                    <p class="info4 col-3"> <span class="info2_lable"><strong><i class="fas fa-hashtag"></i></strong></span>
                                        <span class="time">${posts.id}</span> </p>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>
                    <div class="clear"></div>
                </c:forEach>
            </table>
        </main>
        <c:choose>
            <c:when test="${requestScope.totalPages != 0}">
                <nav class="mt-3 mb-5">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${requestScope.page == 1 ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/homeView?page=${requestScope.page - 1}">
                                Trang trước
                            </a>
                        </li>

                        <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.page == i}">
                                    <li class="page-item active">
                                        <a class="page-link">${i}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/homeView?page=${i}">
                                                ${i}
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/homeView?page=${requestScope.page + 1}">
                                Trang sau
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:when>
            <c:otherwise>
                <h1 style="color: #0a53be">Không có bài đăng ! Vui lòng chọn mục khác</h1>
            </c:otherwise>
        </c:choose>

    </div>

</div>
</body>
</html>
