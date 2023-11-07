<%--
    Document   : contractContent
    Created on : Oct 23, 2023, 10:58:23 PM
    Author     : Alpha
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            function loadDataFromServer(data) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/loadAddressName',
                    method: 'GET',
                    data: data,
                    success: function(response) {
                        $("#wardName").text(response[0]);
                        $("#districtName").text(response[1]);
                        $("#provinceName").text(response[2]);
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

            console.log(postData)
            loadDataFromServer(postData);
        });
        function updateDate() {
            var currentDate = new Date();
            var day = currentDate.getDate();
            var month = currentDate.getMonth() + 1;
            var year = currentDate.getFullYear();

            document.getElementById("day").textContent = day;
            document.getElementById("month").textContent = month;
            document.getElementById("year").textContent = year;
        }

        // Cập nhật tự động mỗi giây
        setInterval(updateDate, 1000);

    </script>
    <title></title></head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>


<div class="contract-detail container">

    <div class="contract-content container">
        <h5>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h5>
        <p>Độc lập – Tự do – Hạnh phúc</p>
        <p>_________________________________________</p>
        <h5><strong>THOẢI THUẬN THUÊ PHÒNG </strong></h5>
        <p>                  -                 </p>
        <p>
            Hôm nay : ngày <span id="day"></span> tháng <span id="month"></span> năm <span id="year"></span><br>
            tại địa chỉ : <span>CTY TNHH Search Home</span> <br>
            <strong>Chúng tôi gồm:</strong><br>
            1.Đại diện bên thuê phòng  <br>
            (Bên A):<br>
            Ông/bà :  ${requestScope.user.fullname} <br>
            Số CMND : ${requestScope.user.cccd}  <br>
            Số điện thoại : ${requestScope.user.phoneNumber}  <br>
            2. Bên thuê phòng trọ (Bên B):<br>
            Ông/bà: <span class="text-primary">${sessionScope.currentUser.fullname}</span>
            CMND số: ${sessionScope.currentUser.cccd} <br>
            Số điện thoại : ${sessionScope.currentUser.phoneNumber} <br>
            <strong> Sau khi bàn bạc trên tinh thần dân chủ, hai bên cùng có lợi, cùng thống nhất như sau :</strong><br>
            Bên A đồng ý thuê 01 phòng ở tại địa chỉ : <span> ${requestScope.post.addressDetail}</span>, <span id="wardName"></span>, <span id="districtName"></span>, <span id="provinceName"></span> <br>
            Giá thuê: ${requestScope.post.price}K vnđ/tháng<br>
            Giá điện: ${requestScope.post.price} vnđ/tháng<br>
          Giá nước: ${requestScope.post.price} vnđ/tháng<br>

<%--            Hôm nay : ngày <span id="day"></span> tháng <span id="month"></span> năm <span id="year"></span><br>--%>
<%--            đến ngày ….. tháng …. năm 20….<br><br>--%>
            <strong>TRÁCH NHIỆM CỦA CÁC BÊN</strong><br>
            <strong>* Trách nhiệm của bên B:</strong><br>
            - Tạo mọi điều kiện thuận lợi để bên A thực hiện theo hợp đồng.<br>
            - Cung cấp nguồn điện, nước, wifi cho bên A sử dụng.<br>
            <strong>* Trách nhiệm của bên A:</strong><br>
            - Thanh toán đầy đủ các khoản tiền theo đúng thỏa thuận.<br>
            - Bảo quản các trang thiết bị và cơ sở vật chất của bên B trang bị cho ban đầu (làm hỏng phải sửa, mất phải đền).<br>
            - Không được tự ý sửa chữa, cải tạo cơ sở vật chất khi chưa được sự đồng ý của bên A.<br>
            - Giữ gìn vệ sinh trong và ngoài khuôn viên của phòng trọ.<br>
            - Bên B phải chấp hành mọi quy định của pháp luật Nhà nước và quy định của địa phương.<br>
            - Nếu bên B cho khách ở qua đêm thì phải báo và được sự đồng ý của chủ nhà đồng thời phải chịu trách nhiệm về các hành vi vi phạm pháp luật của khách trong thời gian ở lại.<br><br>
            <strong>TRÁCH NHIỆM CHUNG</strong>
            - Hai bên phải tạo điều kiện cho nhau thực hiện hợp đồng.<br>
            - Trong thời gian hợp đồng còn hiệu lực nếu bên nào vi phạm các điều khoản đã thỏa thuận thì bên còn lại có quyền đơn phương chấm dứt hợp đồng; nếu sự vi phạm hợp đồng đó gây tổn thất cho bên bị vi phạm hợp đồng thì bên vi phạm hợp đồng phải bồi thường thiệt hại.<br>
            - Một trong hai bên muốn chấm dứt hợp đồng trước thời hạn thì phải báo trước cho bên kia ít nhất 30 ngày và hai bên phải có sự thống nhất.<br>
            - Bên B phải trả lại tiền đặt cọc cho bên chủ phòng tro.<br>
            - Bên nào vi phạm điều khoản chung thì phải chịu trách nhiệm trước pháp luật.<br>
            - Hợp đồng được lập thành 02 bản có giá trị pháp lý như nhau, mỗi bên giữ một bản.<br>
        </p>
        <div class="row container">
            <div class="sign col-6">
<%--                <h4><strong>Kí tên bên A</strong></h4>--%>
<%--                <p>Bên A</p>--%>
            </div>
            <div class="sign col-6">
<%--                <h4><strong>Kí tên bên B</strong></h4>--%>
<%--                <p>Bên B</p>--%>
            </div>
        </div>

        <!--Quay lại & Kí tên-->
        <form action="${pageContext.request.contextPath}/vietqr" method="post">
            <input type="hidden"
                   name="createPaying"
                   value="createPaying">
            <input type="hidden"
                   name="userId"
                   value="${sessionScope.currentUser.id}" >
            <input type="hidden"
                   name="postId"
                   value="${requestScope.post.id}">
            <input type="hidden"
                   name="price"
                   value="${requestScope.post.price}">
            <div class="row submit-button">
                <a class="col-4 appoint" href="${pageContext.request.contextPath}/detail?id=${requestScope.post.id}">Quay lại</a>
                <button style="background-color:#1E3570; color:white" class="col-4 rent ">Thanh toán để xác nhận đơn hàng </button>
            </div>
        </form>

    </div>
</div>
</body>
</html>
