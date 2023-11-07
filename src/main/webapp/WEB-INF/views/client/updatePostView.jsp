<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 11/3/2023
  Time: 8:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<html>
<head>
    <title>Chỉnh sửa bài đăng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    <jsp:include page="metaClient.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

    <script>
        $(function() {
            var imagesPreview = function(input, placeToInsertImagePreview) {

                if (input.files) {
                    var filesAmount = input.files.length;

                    for (i = 0; i < filesAmount; i++) {
                        var reader = new FileReader();

                        reader.onload = function(event) {
                            $($.parseHTML('<img>')).attr('src', event.target.result).appendTo(placeToInsertImagePreview).css({'width': '100px', 'height': '100px'});
                        }

                        reader.readAsDataURL(input.files[i]);
                    }
                }

            };

            $('#gallery-photo-add1').on('change', function() {
                imagesPreview(this, 'div.gallery1');

            });
            $('#gallery-photo-add2').on('change', function() {
                imagesPreview(this, 'div.gallery2');

            });
        });
        function showToast(title, message, isSuccess) {
            const toastElement = document.getElementById('liveToast');
            const toastHeader = document.getElementById('toastHeader');
            const toastBody = document.getElementById('toastBody');

            // Set the title and message
            toastHeader.textContent = title;
            toastBody.textContent = message;

            // Set the color based on success or error
            toastElement.classList.remove('bg-success', 'bg-danger');
            toastElement.classList.add(isSuccess ? 'bg-success' : 'bg-danger');

            // Create a new toast instance and show it
            var toast = new bootstrap.Toast(toastElement);
            toast.show();
        }

        <% if (request.getAttribute("successMessage") != null) { %>
        // Call showToast with success message
        showToast("Success", "<%= request.getAttribute("successMessage") %>", true);
        <% } else if (request.getAttribute("errorMessage") != null) { %>
        // Call showToast with error message
        showToast("Error", "<%= request.getAttribute("errorMessage") %>", false);
        <% } %>
    </script>
    <style>

        td,th,tr{
            font-size:12px;
        }

        .progress {
            margin: 20px 0px;
        }
        .progress-bar {
            display: flex;
            flex-direction: column;
            justify-content: center;
            overflow: hidden;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            background-color: #0d6efd;
            transition: width 0.6s ease;
        }
        @media (prefers-reduced-motion: reduce) {
            .progress-bar {
                transition: none;
            }
        }

        .progress-bar-striped {
            background-image: linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
            background-size: 1rem 1rem;
        }

        .progress-bar-animated {
            -webkit-animation: 1s linear infinite progress-bar-stripes;
            animation: 1s linear infinite progress-bar-stripes;
        }
        @media (prefers-reduced-motion: reduce) {
            .progress-bar-animated {
                -webkit-animation: none;
                animation: none;
            }
        }
        .number-tag {
            /* margin: 10px; */
            padding: 3px 6px;
            box-sizing: content-box;
            border-radius: 100%;
            background-color: blue;
            color: white;
        }

        .guide {
            margin: 10px 0px;
            background-color: #f9f9f9;
        }

        th,
        tr {
            border: 1px solid;
            margin: 20px 0px;
            padding: 7px 10px;
            box-sizing: content-box;
        }

        td{
            padding:10px;

        }

        .create_post {
            padding: 20px;
        }


        .notion {
            background-color: rgb(237, 206, 206);
            color: rgb(229, 40, 40);
            height: 60px;
            display: flex;
            align-items: center;
            border-radius: 10px;
        }

        .progress {
            margin: 20px 0px;
        }


        #pro_description {
            height: 160px;
            width: 688px;
        }

        .item{
            margin-top: 8px;
        }


        .red {
            color: red;
        }

        #imageloadbutton{
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            padding:10px;
            box-sizing: content-box;
            line-height: 33px;
            width: 666px;
            /* này dùng để dịch sang */
            margin-left: 12px;
        }


        body{
            margin: 0;
            padding: 0;
            color: #333;
            font-family: Arial, Helvetica, Sans-serif;
            font-size: 12px;
            line-height: 18px;
        }

        a, h1, h2, h3, h4, h5, h6, li, p, ul {
            margin: 0;
            padding: 0;
            text-decoration: none!important;
            color: #1a3560;
        }

        hr {
            margin: 5px 0px;
        }

        p {
            margin: 0px;
        }

        label {
            float: left;
            width: 100px;
            line-height: 36px;
            min-height: 10px;
        }

        input,select {
            border-radius: 1px;
            border: 1px solid #dfdfdf;
            padding: 5px;
            width: 688px;
            height: 33px;
            margin:8px 0px;
            position: relative;
            /* right:10px */
        }


        textarea {
            border: 1px solid #bdc7d8;
            height: 160px;
            width: 100px;
            position: relative;
            padding: 5px;
            /* right: 10px; */
        }

        textarea #pro_description {
            width: 688px;
        }

        .camera-image {
            background-image: url('img/bg_upload_1.png');
            background-repeat: no-repeat;
            width: 100%;
            height: 90px !important;
            background-size:contain;
        }

        * {
            margin: 0px;
            box-sizing: border-box;
        }



        .btnSubmit {
            background: #f57301 !important;
            color: #fff !important;
            padding: 15px 0;
            display: block;
            font-size: 15px;
            border: none;
            float: left;
            border-radius: 5px;
            width: 200px;
            text-align: center;
            height: 50px;
        }



    </style>

</head>

<body>
<%@include file="_header.jsp" %>

<c:choose>
<c:when test="${not empty sessionScope.currentUser}">
<div class="container">
    <div class="row">
        <div class="container mt-3">

        </div>
        <div class="col-3">
            <div class="guide">
                <table>
                    <tr>
                        <th class="title">
                            <p><strong>Hướng dẫn đăng tin</strong></p>
                        </th>
                    </tr>
                    <tr>
                        <td><strong>Nội dung tốt</strong>
                            <ul>
                                <p>- Nhập ảnh thật được nhiều người xem hơn
                                </p>
                                <p>- Nhập đầy đủ địa chỉ nhà đất cho thuê
                                </p>
                                <p>- Nhập đầy đủ diện tích nhà đất cho thuê
                                </p>
                                <p>- Nhập đầy đủ giá cả, chứng nhận pháp lý
                                </p>
                                <p>- Miêu tả những điểm nổi bật, đặc thù của bất động sản.
                                </p>
                            </ul>
                            <strong>Nội dung không tốt</strong>
                            <ul>
                                <p>- Thông tin trùng lặp với bài đã đăng</p>
                                <p>- Thiếu địa chỉ nhà đất đất cụ thể</p>
                                <p>- Thiếu diện tích cụ thể</p>
                                <p>- Thiếu giá cả, chứng nhận pháp lý cụ thể</p>
                            </ul>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-9">
            <form method="post"
                  action="${pageContext.request.contextPath}/updatePostFromUser"
                  class="user_form "
                  enctype="multipart/form-data">
                <input type="hidden"
                       name="userId"
                       value="${sessionScope.currentUser.id}" >
                <input type="hidden" value="${requestScope.post.id}" name="id">
                <div class="row item_sub item_sub_first">
                    <label class="col-2" ><strong>Chọn loại</strong></label>
                    <div class=" col-9 item_input">
                        <label>
                            <select class="input_select pro_city_id"  name="roomTypeId">
                                <option ${empty requestScope.roomTypeId || requestScope.roomTypeId == 0 ? 'selected' : ''} disabled>
                                    Chọn một thể loại...
                                </option>
                                <c:forEach items="${requestScope.roomTypes}" var="type">
                                    <option value="${type.id}" ${requestScope.roomTypeId == type.id ? 'selected' : ''}>
                                            ${type.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <c:if test="${not empty requestScope.violations.roomTypesViolations}">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.roomTypesViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </label>

                    </div>
                </div>
                <div class="item row">
                    <label class="col-2" for="gallery-photo-add1"><strong>Ảnh chính </strong></label>
                    <div class="col-9" id="imageloadbutton">
                        <p><b>"Một hình ảnh bằng cả ngàn lời nói". Hãy đăng ảnh để được nhiều người xem hơn.</b></p>
                        <i>Chọn ảnh đại diện</i>
                        <label for="gallery-photo-add1"
                               class="camera-image">
                            <input type="file"
                                   name="image"
                                   accept="image/*"
                                   id="gallery-photo-add1"
                                   style="display: none;"
                                   value=""
                            />
                        </label>
                        <div class="gallery1"></div>
                        <c:choose>
                            <c:when test="${not empty requestScope.post.imageName}">
                                <img width="200"
                                     class="img-thumbnail mb-3"
                                     src="${pageContext.request.contextPath}/image/${requestScope.post.imageName}"
                                     alt="${requestScope.post.imageName}"
                                     title="${requestScope.post.imageName}">
                                <div class="mb-3 form-check">
                                    <input class="form-check-input"
                                           type="checkbox"
                                           value="deleteImage"
                                           id="delete-image"
                                           name="deleteImage" ${not empty requestScope.deleteImage ? 'checked' : ''}>
                                    <label class="form-check-label" for="delete-image">Xóa hình này?</label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="fst-italic mb-3">Không có hình</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="item row">
                    <label class="col-2" for="gallery-photo-add2"><strong> Ảnh chi tiết </strong></label>
                    <div class="col-9" >
                        <i>Chọn ảnh chi tiết </i>
                        <input type="file"
                               name="images[]"
                               id="gallery-photo-add2"
                               accept="image/*"
                               multiple = "multiple"
                               style=""
                               value="">
                        <div class="gallery2"></div>
                        <c:forEach var="image" varStatus="loop" items="${requestScope.image}">
                            <img width="200"
                                 class="img-thumbnail mb-3"
                                 src="${pageContext.request.contextPath}/image/${image.imageName}"
                                 alt="${image.imageName}"
                                 title="${image.imageName}">
                        </c:forEach>


                    </div>

                </div>
                <div class="item row">
                    <label class="col-2">Title<span class="red">(*)</span></label>
                    <div class="col-9 item_input">
                        <input name="title"
                               class="pro_title"
                               type="text"
                               id="pro_title"
                               value="${requestScope.post.title}"
                               placeholder="Cho thuê nhà trọ, phòng trọ ở đâu...">
                        <c:if test="${not empty requestScope.violations.titleViolations}">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.titleViolations}">
                                    <li style="color: red">${violation}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>

                </div>


                <div class="item row">
                    <label class="col-2">Description <span class="red">(*)</span></label>
                    <div class="col-9 item_input">
                        <textarea name="descriptions" class="input_des pro_description" id="pro_description"
                                  placeholder="Mô tả thêm thông tin, tiện ích, qui định, giá cả...">
                                ${requestScope.post.descriptions}
                        </textarea>
                        <c:if test="${not empty requestScope.violations.descriptionViolations}">
                            <ul class="list-unstyled" >
                                <c:forEach var="violation" items="${requestScope.violations.descriptionViolations}">
                                    <li style="color: red">${violation}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>

                </div>


                <div class="item row">
                    <jsp:include page="addressView.jsp"/>

                </div>

                <div class="row item">
                    <div class="row item_sub item_sub_first">
                        <label class="col-2">Diện tích(m&#178)</label>
                        <div class=" col-9 item_input">
                            <input name="area" class="pro_acreage" type="number" id="pro_acreage" value="${requestScope.post.area}">
                            <c:if test="${not empty requestScope.violations.areaViolations}">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.areaViolations}">
                                        <li style="color: red">${violation}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>

                    </div>


                    <div class="row item_sub item_sub_last">
                        <label class="col-2">Địa chỉ</label>
                        <div class=" col-9 item_input">
                            <input name="addressDetail" class="pro_addr" type="text" id="pro_addr" value="${requestScope.post.addressDetail}">
                            <c:if test="${not empty requestScope.violations.addressDetailViolations}">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.addressDetailViolations}">
                                        <li style="color: red">${violation}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>

                    </div>



                </div>



                <div class="item">
                    <div class="item_sub item_sub_first row">
                        <label class="col-2">Giá <span class="red">(*)</span></label>
                        <div class="col-9 item_input">
                            <input name="price" class="pro_price" type="number" id="pro_price" value="${requestScope.post.price}">
                        </div>
                    </div>
                    <div class="item_sub">
                        <b class="pro_price_view red"></b>
                    </div>
                    <c:if test="${not empty requestScope.violations.priceViolations}">
                        <ul class="list-unstyled">
                            <c:forEach var="violation" items="${requestScope.violations.priceViolations}">
                                <li style="color: red">${violation}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>

                <input type="hidden" name="imageName" value="${requestScope.post.imageName}">


                <div class="item_message">
                    <label>&nbsp;</label>
                    <p class="message"></p>
                </div>

                <div class="item">
                    <label>&nbsp;</label>
                    <input type="hidden" value="" id="pro_avatar" class="pro_avatar" name="pro_avatar" />
                    <input type="hidden" value="10" class="pro_cat_id" name="pro_cat_id" />
                    <input type="hidden" value="" id="pro_avatar_list" class="pro_avatar_list"
                           name="pro_avatar_list" />
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Chỉnh sửa " class="btnSubmit" />
                </div>

                <div class="clear"></div>
            </form>
        </div>
    </div>
</div>
</c:when>
<c:otherwise>
    <%@include file="signinView.jsp" %>
</c:otherwise>
</c:choose>




<%@include file="_footer.jsp" %>

