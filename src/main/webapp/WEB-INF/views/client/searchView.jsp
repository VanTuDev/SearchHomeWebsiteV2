<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="metaClient.jsp"/>
    <title>Kết quả tìm kiếm cho "${requestScope.query}"</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-content mb-5">
    <div class="container">
        <header class="section-heading py-4">
            <h3 class="section-title">
                Kết quả tìm kiếm cho "${requestScope.query}": ${requestScope.totalPost} Bài đăng
            </h3>
        </header> <!-- section-heading.// -->

        <div class="row item-grid">
            <div>
                <main class="table-responsive-xl mb-5">
                    <table class="table table-bordered table-striped table-hover align-middle">

                        <c:forEach var="posts" varStatus="loop" items="${requestScope.posts}">
                            <div class="list_pro">
                                <div class="pro_item"> <a class="img" >
                                    <c:choose>
                                        <c:when test="${empty posts.imageName}">
                                            <img style="max-height: 500px; min-height: 400px" src="${pageContext.request.contextPath}/img/50px.png"
                                                 alt="50px.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img style="max-height: 500px; min-height: 400px" src="${pageContext.request.contextPath}/image/${posts.imageName}"
                                                 alt="${posts.imageName}">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                    <div class="info">
                                        <h5> <a href="${pageContext.request.contextPath}/detail?id=${posts.id}" role="button"> ${posts.title}</a></h5>
                                        <div class="group2">
                                            <p class="info_adrr">
                                                <span class="info2_lable"><i class="fas fa-map-marker"></i></span>
                                                <span class="info2_lable3"><span>${posts.addressDetail}, ${posts.wardCode}, ${posts.districtCode}, ${posts.provinceCode}</span></span> </p>
                                        </div>
                                        <div class="intro"> ${posts.descriptions} </div>
                                        <div class="group2">
                                            <p class="info3"> <span class="info2_lable"><strong><i class="fas fa-comment-dollar"></i></strong></span>
                                                <span class="info2_lable3">${posts.price}&#8363;</span> </p>
                                            <p class="info3"> <span class="info2_lable"><strong><i class="fas fa-chart-area"></i></strong></span>
                                                <span class="info2_lable3">${posts.area}&#178;</span> </p>
                                            <p class="info4"> <span class="info2_lable"><strong><i class="fas fa-calendar-check"></i></strong></span>
                                                <span class="time">${posts.createAt}</span> </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                        </c:forEach>
                    </table>
                </main>

                <c:if test="${requestScope.totalPages != 0}">
                    <nav class="mt-4">
                        <ul class="pagination">
                            <li class="page-item ${requestScope.page == 1 ? 'disabled' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${requestScope.page - 1}">
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
                                               href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${i}">
                                                    ${i}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${requestScope.page + 1}">
                                    Trang sau
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div> <!-- container.// -->


        </div>

        <jsp:include page="_footer.jsp"/>
</body>

</html>