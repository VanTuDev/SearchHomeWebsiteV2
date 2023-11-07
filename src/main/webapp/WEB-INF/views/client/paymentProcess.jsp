<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Thanh toán</title>
  <jsp:include page="metaClient.jsp"/>
  <style>
    #countdown {
      background-color: #007BFF;
      color: #FFFFFF;
      text-align: center;
      padding: 10px;
    }

    #countdownValue {
      font-size: 2rem;
    }

    #qrCodeImage {
      max-width: 100%;
      display: block;
      margin: 0 auto;
    }

  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      let countdownSeconds = 900;
      updateCountdown();
      const countdownInterval = setInterval(function () {
        countdownSeconds--;
        updateCountdown();
        if (countdownSeconds <= 0) {
          regenerateQRCode();
          countdownSeconds = 900;
        }
      }, 1000);

      function updateCountdown() {
        const minutes = Math.floor(countdownSeconds / 60);
        const seconds = countdownSeconds % 60;
        const countdownText = minutes + " phút " + seconds + " giây";
        $("#countdown").text("Thời gian còn lại: " + countdownText);
      }

    });
    $(document).ready(function() {
      let countdownSeconds = 900;
      updateCountdown();
      const countdownInterval = setInterval(function () {
        countdownSeconds--;
        updateCountdown();
        if (countdownSeconds <= 0) {
          regenerateQRCode();
          countdownSeconds = 900;
        }
      }, 1000);
      function updateCountdown() {
        const minutes = Math.floor(countdownSeconds / 60);
        const seconds = countdownSeconds % 60;
        const countdownText = minutes + " phút " + seconds + " giây";
        $("#countdown").text("Thời gian còn lại: " + countdownText);
      }
      trySendDataToGoogle();
    });
    function trySendDataToGoogle() {
      const payingId = $("#payingId").val();
      const description = $("#description").val();
      if (payingId !== "" && description !== "") {
        sendDataToGoogle(payingId, description);
      }
    }
    function sendDataToGoogle(payingId, description) {
      const dataToSend = {
        payingId: payingId,
        description: description
      };

      $.ajax({
        type: 'POST',
        url: '${pageContext.request.contextPath}/googleReadMail',
        data: dataToSend,
        success: function(response) {
          console.log(response);
          if (response.statusCode === 200) {
            console.log("Đã thanh toán thành công");
            const successModal = $("#successModal");
            const successMessageModal = $("#successMessageModal");
            successMessageModal.text(response[0]);
            successModal.modal("show");
          }

        },
        error: function(xhr, status, error) {
          console.error(error);
        }
      });
    }
  </script>
</head>
<%@include file="_header.jsp" %>
<br>
<br>
<br>


<body>
<div class="container">
  <div class="row">
    <div class="col-md-6 border mx-auto my-auto">
      <!-- Your existing content here -->
      <a href="${pageContext.request.contextPath}/googleapi" class="btn btn-primary my-2">Xem lịch sử giao dịch</a>
      <div id="countdown" class="bg-primary text-white text-center p-3">
        <h4>Thời gian còn lại:</h4>
        <span id="countdownValue" class="display-4"></span>
      </div>
      <div class="alert alert-info my-3">
        Quý khách vui lòng không tắt trình duyệt cho đến khi nhận được kết quả giao dịch trên website.
      </div>
      <img id="qrCodeImage" src="${requestScope.qrdataURL}" alt="${requestScope.qrdataURL}" class="img-fluid mx-auto d-block my-3">
      <input type="hidden" value="${requestScope.randomId}">
      <p class="my-3">
        Vui lòng thanh toán và đặt mô tả theo cú pháp sau để hệ thống xác nhận đơn :
        <span>${requestScope.randomDescription}</span>
      </p>
      <form action="" id="myform">
        <input id="payingId" type="hidden" name="payingId" value="${requestScope.payingId}">
        <input id="description" type="hidden" name="randomDescription" value="${requestScope.randomDescription}">
      </form>
      <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="successModalLabel">Thành công</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p id="successMessageModal">Đã thanh toán thành công!</p>
              <a href="${pageContext.request.contextPath}/paymentHistory?id=${requestScope.userId}" class="btn btn-primary">Đi tới trang quản lý hoá đơn</a>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>

<%@include file="_footer.jsp" %>
