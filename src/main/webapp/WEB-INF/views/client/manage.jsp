<%--
  Created by IntelliJ IDEA.
  User: Alpha
  Date: 06/11/2023
  Time: 9:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Setting</title>
    <style>
        a{
            text-decoration: none;
            color: #306FC2;
        }

        .item-menu{
            margin: 10px 0px;
            margin-right: 3px;
        }

        .item-menu p{
            margin: 10px;
            padding:3px;
            box-sizing: border-box;
            border-radius: 5px;
        }

        .item-menu .active{
            background-color: #306fc21a;
            width: 100%;
            height: 30px;
        }

        .menu-report {
            width: 100%;
        }

        .custom-container {
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
        }

        .report{
            padding: 15px;
            border-radius: 10px;
            box-sizing: content-box;
        }

        .bg-success{
            background-color: rgba(122, 252, 122, 0.414)!important;
        }

        .content{
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            position: relative;
            border-radius: 10px;
        }
        .custom-container ul {
            list-style: none;
            padding: 0;
        }

        .custom-container li {
            margin: 5px 0;
        }

        .fa-star {
            color: #f7c719;
        }

        .delete-button {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            cursor: pointer;
        }

        .icon-trash{
            padding: 2px;
            border: 1px solid;
            color:rgb(229, 92, 92);
            border-radius: 5px;
        }

        .icon-view{
            padding: 2px;
            border: 1px solid;
            border-radius: 5px;
        }

        .report-title{
            margin-bottom: 5px;
            display: flex;
            align-items: center;
        }

        .report-title p{
            margin:0px
        }

        .group2 p{
            margin: 0px;
        }

        .pagination{
            margin: 0px;
        }

        .report-content{
            margin:0px;
        }

        .number{
            background-color: #7caae7;
            color:aliceblue;
        }

        .page-link{
            margin:0px 5px;
        }

        .report-content img{
            /* height: ; */
            background-size: contain;
        }

        .camera-image {
            background-image: url('../img/bg_upload_1.png'  );
            background-repeat: no-repeat;
            width: 100%; /* Điều chỉnh kích thước hình ảnh tùy ý */
            height: 90px;
        }

        #imageloadbutton{
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            padding:10px;
            box-sizing: content-box;
            line-height: 33px;
            /* width: 100%; */
            /* này dùng để dịch sang */
            /* margin-left: 12px; */
        }

        /* effect */
        .item-menu p.active a {
            color: #007BFF; /* Change the color to blue for the active link */
        }

        /* Add hover effect to menu links */
        .item-menu p a:hover {
            color: #FF5733; /* Change the color to orange on hover */
        }

        /* Add hover effect to the "Update" button */
        .btn-primary.hvr-fade:hover {
            background-color: #FF5733; /* Change the background color to orange on hover */
            color: white; /* Change the text color to white on hover */
            transition: background-color 0.3s, color 0.3s; /* Smooth transition effect */
        }

        .item-menu p.active {
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
        }

        .btn-primary.hvr-fade {
            transition: background-color 0.3s, color 0.3s; /* Add a smooth transition effect */
        }

        /* Style the radio button container (each span) */
        .form-group span {
            display: inline-block;
            margin-right: 10px; /* Add some spacing between options */
        }

        /* Style the radio buttons */
        .form-group input[type="radio"] {
            margin-right: 5px; /* Add some spacing between the radio button and the label */
            vertical-align: middle; /* Vertically align the radio button with the label text */
        }

        /* Style the label text */
        .form-group label {
            display: block;
            margin-bottom: 5px; /* Add spacing below the label */
        }

        .camera-image {
            transition: transform 0.3s; /* Add a smooth transition effect */
        }

        /* Apply a scale effect on hover */
        .camera-image:hover {
            transform: scale(1.008); /* Slightly increase the size on hover */
        }

        .delete-button:hover .icon-view{
            background-color: rgba(92, 229, 92, 0.7); /* Change the background color on hover */
            color:white
        }

        .delete-button:hover .icon-trash {
            background-color: rgba(229, 92, 92, 0.7); /* Change the background color on hover */
            color: #f9f9f9;
        }

        .item-menu p a:hover {
            transform: scale(1.05); /* Slightly increase the size on hover */
        }

        .btn-primary.hvr-fade {
            transition: background-color 0.3s, color 0.3s; /* Add a smooth transition effect */
        }
    </style>
    <jsp:include page="metaClient.jsp"/>
</head>

<body>
<%@include file="_header.jsp" %>
<div class="container">
    <div class="row">
        <div class="item-menu col-3">
            <p><a href="setting.html"><i class="fas fa-user"></i> Tài khoản</a></p>
            <p><a href="pass.html"><i class="fas fa-user-lock"></i> Đổi mật khẩu</a></p>
            <p><a href="notification.html"><i class="fas fa-bell"></i> Thông báo</a></p>
            <p class="active"><a href="#"><i class="fas fa-newspaper"></i> Quản lý hợp đồng</a></p>
            <p><a href="report.html"><i class="fas fa-times-circle"></i> Đánh giá bài đăng</a></p>
            <p><a href="wishlist.html"><i class="fas fa-heart"></i> Bài đăng yêu thích</a></p>
        </div>
        <div class="col-8">
            <div class="menu-report ">
                <h1><b>Quản lý hợp đồng</b></h1>
                <div class="access"></div>
                <div class="custom-container">
                    <div class="bg-success">
                        <div class="container row report">
                            <div class="report-title row">
                                <b class="col-5"><i class="fas fa-home"></i> Nhà đẹp ở quận Ngũ Hành Sơn </b>
                                <a class="col-3">04:16:05 04/10/2021</a>
                                <div class="delete-button col-4" onclick="closeContainer(this)"><span
                                        class="icon-view"><i class="fas fa-eye"></i> Xem chi tiết</span>
                                </div>
                            </div>
                            <hr>
                            <a href="">
                                <div class="report-content row">
                                    <img class="col-5"
                                         src="https://tostemvietnam.com/wp-content/uploads/2023/05/nha-dep-2-1.png"
                                         alt="ảnh nhà">
                                    <div class="col-7">
                                        <div class="group2">
                                            <p class="info_adrr">
                                                <span class="info2_lable"><i class="fas fa-map-marker"></i></span>
                                                <span class="info2_lable3"> detail address </span> </p>
                                        </div>
                                        <div class="intro"> CHO THUÊ NHÀ GÓC 2 MẶT TIỀN 64 ĐINH TIÊN HOÀNG - NGUYỄN VĂN
                                            THỦ
                                            QUẬN 1.!!! GẦN CÔNG
                                            VIÊN LÊ VĂN TÁM VÀ MCDONAL'S ĐA KAO.!! KHU VỰC TẬP TRUNG CÁC NHÀ HÀNG, VĂN
                                            PHÒNG
                                            TRỤ SỞ VÀ THƯƠNG
                                            HIỆU LỚN.!!! - CÓ BÃI GIỮ XE
                                            24/24.!! - Diện tích xây dựng: 16m x 6m - Giá cho thuê là 70tr/tháng - Kết
                                            Cấu: 3 tầng, 1 ... </div>
                                        <div class="group2 row">
                                            <p class="info3 col-4"> <span class="info2_lable"><strong><i
                                                    class="fas fa-chart-area"></i></strong></span>
                                                <span class="info2_lable3">288 m&#178;</span> </p>
                                            <p class="info3 col-4"> <span class="info2_lable"><strong><i
                                                    class="fas fa-map"></i></strong></span>
                                                <span class="info2_lable3">288 m&#178;</span> </p>
                                            <p class="info4 col-4"> <span class="info2_lable"><strong><i
                                                    class="fas fa-calendar-check"></i></strong></span>
                                                <span class="time">18/10/2023</span> </p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item active"><a class="page-link number" href="#">1</a></li>
                        <li class="page-item"><a class="page-link number" href="#">2</a></li>
                        <li class="page-item"><a class="page-link number" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="_footer.jsp" %>
</body>

</html>