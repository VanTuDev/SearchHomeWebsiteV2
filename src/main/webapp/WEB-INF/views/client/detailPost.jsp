


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <title>Chi tiết bài đăng</title>
    <jsp:include page="metaClient.jsp"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            function loadDataFromServer(data) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/loadAddressName',
                    method: 'GET',
                    data: data,
                    success: function(response) {
                        $(".wardName").text(response[0]);
                        $(".districtName").text(response[1]);
                        $(".provinceName").text(response[2]);
                    },
                    error: function() {
                        console.log("Error loading data.");
                    }
                });
            }


            const postData = {
                wardCode: "${requestScope.post.wardCode}",
                districtCode: "${requestScope.post.districtCode}",
                provinceCode: "${requestScope.post.provinceCode}"
            };

            loadDataFromServer(postData);
        });

    </script>
    <script>

        const REQUIRED_SIGNIN_MESSAGE = "Vui lòng đăng nhập để thực hiện thao tác!";

        // COMPONENTS
        function toastComponent(message, color = "primary") {
            return `
    <div class="toast align-items-center text-white bg-${color} border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
          ${message}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  `;
        }

        // UTILS
        function _showToast() {
            const hiddenToastElements = [].slice.call(document.querySelectorAll(".toast.hide"));
            hiddenToastElements.forEach((hiddenToastElement) => hiddenToastElement.remove());

            const toastElements = [].slice.call(document.querySelectorAll(".toast:not(.hide)"));
            const toasts = toastElements.map((toastElement) => new bootstrap.Toast(toastElement));
            toasts.forEach((toast) => toast.show());
        }

        // MAIN
        function createToast(toastComponent) {
            const toastContainerElement = document.querySelector(".toast-container");
            toastContainerElement.insertAdjacentHTML("beforeend", toastComponent);
            _showToast();
        }


        const SUCCESS_ADD_WISHLIST_ITEM_MESSAGE = (productTitle) =>
            `Đã thêm thành công bài đăng tên  ${requestScope.title} vào danh sách yêu thích!`;

        const FAILED_ADD_WISHLIST_ITEM_MESSAGE = "Đã có lỗi truy vấn!";
        const contextPathMetaTag = document.querySelector("meta[name='contextPath']");
        const currentUserIdMetaTag = document.querySelector("meta[name='currentUserId']");
        const postIdMetaTag = document.querySelector("meta[name='postId']");

        async function _fetchPutAddWishlistItem() {
            const wishlistItemRequest = {
                userId: currentUserIdMetaTag.content,
                productId: postIdMetaTag.content,
            };

            const response = await fetch(contextPathMetaTag.content + "/wishList", {
                method: "PUT",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(wishlistItemRequest),
            });

            return [response.status, await response.json()];
        }
        function noneSigninEvent() {
            createToast(toastComponent(REQUIRED_SIGNIN_MESSAGE));
        }
        async function addWishlistItemBtnEvent() {
            const [status] = await _fetchPutAddWishlistItem();
            if (status === 200) {
                createToast(toastComponent(
                    SUCCESS_ADD_WISHLIST_ITEM_MESSAGE(productTitleElement.innerText), "success"));
                addWishlistItemBtn.disabled = true;
            } else if (status === 404) {
                createToast(toastComponent(FAILED_ADD_WISHLIST_ITEM_MESSAGE, "danger"));
            }
        }
        // MAIN
        const addWishlistItemBtn = document.querySelector("#add-wishlist-item");
        const buyNowBtn = document.querySelector("#buy-now");
        const addCartItemBtn = document.querySelector("#add-cart-item");

        if (currentUserIdMetaTag) {
            addWishlistItemBtn.addEventListener("click", addWishlistItemBtnEvent)

        } else {
            // addWishlistItemBtn.addEventListener("click", noneSigninEvent);

        }

    </script>

</head>
<br>
<body>
<br>
<%@include file="_header.jsp" %>
<div class="container">
    <div class="detail-post row">
        <div class="infor-post row col-md-11">
            <div class="container infor-main row">
                <ol class="col-6 breadcrumb">
                    <li><b> <h3 href="${pageContext.request.contextPath}/homeView">Home/ </h3></b></li>
                    <h3 class="active">${requestScope.post.title}</h3>
                </ol>
                <div class="time-post col-6">
                    <p> <span class="info2_lable"><strong><i class="fas fa-calendar-check"></i></strong></span>
                        <span class="time">${requestScope.post.startAt}</span> </p>
                </div>
                <div id="demo1" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/image/${requestScope.post.imageName}"
                             alt="${requestScope.post.title}" class="d-block" style="width:690px;height: 500px;">
                    </div>
                </div>
            </div>
            <div>
                <h1><strong style="font-size: 30px;">${requestScope.post.title}</strong></h1>
                <address class="col-12"><span><i class="fas fa-map-marked-alt"></i> Địa chỉ:${requestScope.post.addressDetail}, <span class="wardName"></span> , <span class="districtName"></span> ,<span class="provinceName"></span>  </span>
            </div>

            <div class="info_item row">
                <p class="col-3"><span><i class="fas fa-hand-holding-usd"></i></span> <span
                        class="text-danger"><b>${requestScope.post.price} đ</b></span></p>
                <p class="col-3"><span><i class="fas fa-chart-area"></i>
                            </span><span>${requestScope.post.area} cm&#178;</span></p>
                <p class="col-3"><span><i class="fas fa-calendar-alt"></i></span>
                    ${requestScope.post.startAt}</p>
                <p class="col-3"><span><i class="fas fa-hashtag"></i></span>
                    ${requestScope.post.id}</p>
                <hr>
                <div>
                    <h2><span><b>Thông tin mô tả: </b></span></h2>
                    <p>${requestScope.post.descriptions}</p>
                </div>
            </div>
        </div>
    </div>




    <div class="container">
        <section class=" col-11 section post-overview">
            <div class="section-header">
                <h3 class="section-title">Đặc điểm tin đăng</h3>
            </div>
            <div class="section-content">
                <table class="table">
                    <tr>
                        <td class="name">Mã tin:</td>
                        <td>#${requestScope.post.id}</td>
                    </tr>
                    <tr>
                        <td class="name">Khu vực</td>
                        <td>${requestScope.post.addressDetail}, <span class="wardName"></span> , <span class="districtName"></span> ,<span class="provinceName"></span> </td>
                    </tr>
                    <tr>
                        <td class="name">Loại tin rao:</td>
                        <td>${requestScope.post.roomTypeName}</td>
                    </tr>
                    <tr>
                        <td class="name">Đối tượng thuê:</td>
                        <td>Tất cả</td>
                    </tr>
                    <tr>
                        <td class="name">Gói tin:</td>
                        <td><span style="color: #E13427;">Tin VIP nổi bật</span></td>
                    </tr>
                    <tr>
                        <td class="name">Ngày đăng:</td>
                        <td>#${requestScope.post.startAt}</td>
                    </tr>
                    <tr>
                        <td class="name">Ngày hết hạn:</td>
                        <td>#${requestScope.post.endAt}</td>
                    </tr>
                </table>
            </div>
        </section>
    </div>



    <section class=" col-12 section post-overview">
        <h3>Hình ảnh chi tiết</h3>
        <div class="section-header">
            <div id="demo" class="carousel slide" data-bs-ride="carousel">
                <!-- Indicators/dots -->
                <ol class="carousel-indicators">
                    <c:forEach var="image" varStatus="loop" items="${requestScope.image}">
                        <li data-bs-target="#demo" data-bs-slide-to="${loop.index}" class="${loop.first ? 'active' : ''}"></li>
                    </c:forEach>
                </ol>
                <!-- The slideshow/carousel -->
                <div class="carousel-inner">
                    <c:forEach var="image" varStatus="loop" items="${requestScope.image}">
                        <div class="carousel-item ${loop.first ? 'active' : ''}">
                            <img src="${pageContext.request.contextPath}/image/${image.imageName}"
                                 alt="Image ${loop.index}" class="d-block" style="width:100%;height: 500px;">
                        </div>
                    </c:forEach>
                </div>
                <!-- Left and right controls/icons -->
                <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </button>
            </div>
        </div>
    </section>



    <div class="container">
        <div class="other row">
            <div class="col-6">
                <div class="user-infor container-fluid">
                    <div>
                        <h1><b>Được đăng bởi</b></h1>
                        <h2 >Người cho thuê:${requestScope.user.fullname}</h2>
                        <h2 >Email liên hệ:${requestScope.user.email}</h2>
                        <h2 >Số điện thoại:${requestScope.user.phoneNumber}</h2>
                    </div>
                    <img style="width: 400px; width: 400px" src="${requestScope.user.imageName}" alt="Ảnh người dùng"/>

                </div>
            </div>
            <div class="col-6 map">
                <h3><strong>Bản đồ</strong></h3>
                <address class="col-12">
                    <span><i class="fas fa-map-marked-alt"></i> Địa chỉ: <span class="wardName"></span> , <span class="districtName"></span> , <span class="provinceName"></span></span>
                    <span id="wardName"></span>
                    <span id="districtName"></span>
                    <span id="provinceName"></span>
                </address>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d54848.05382530855!2d108.17440819606561!3d16.040734114423124!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314219c792252a13%3A0xfc14e3a044436487!2zxJDDoCBO4bq1bmcsIEjhuqNpIENow6J1LCDEkMOgIE7hurVuZywgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1699037296428!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
    </div>
    <div class="post-button">
        <c:choose>
            <c:when test="${not empty sessionScope.currentUser}">
                <div class="post-button">
                    <button type="button" class="view-button"><a href="contract">Đặt lịch hẹn</a></button>
                    <button type="button" class="contract-button"><a href="${pageContext.request.contextPath}/hopdong?id=${requestScope.post.id}">Tạo hợp đồng</a></button>
                </div>
            </c:when>
            <c:otherwise>
                <button type="button" class="contract-button"><a class="header_user_nologin " href="${pageContext.request.contextPath}/signin" role="button">
                    <i class="fa-solid fa-right-to-bracket"></i> Đăng nhập để ký hợp đồng & đặt lịch hẹn
                </a></button>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</div>
</div>


<%@include file="_footer.jsp" %>
</body>
</html>
