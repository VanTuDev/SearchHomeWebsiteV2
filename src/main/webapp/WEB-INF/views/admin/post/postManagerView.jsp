<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<html>
<head>
    <jsp:include page="../../_meta.jsp"/>
    <title>Create Post</title>
    <c:if test="${not empty requestScope.operationResult}">
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                var operationResult = "${requestScope.operationResult}";
                var successMessage = "${requestScope.successMessage}";
                var errorMessage = "${requestScope.errorMessage}";

                if (operationResult === "success") {
                    // Show success toast
                    var successToast = document.querySelector("#successToast");
                    var successToastInstance = new bootstrap.Toast(successToast);
                    successToastInstance.show();
                }
            });
        </script>
    </c:if>

</head>
<body>



<jsp:include page="../_headerAdmin.jsp"/>
<section class="section-content">
    <jsp:include page="./postCreateView.jsp"/>
</section>

<%--<jsp:include page="../_footerAdmin.jsp"/>--%>
<div class="toast position-fixed bottom-0 end-0 mb-4 mr-4 ml-4 alert-success" id="successToast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
        <strong class="me-auto">Success</strong>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
        ${successMessage}
    </div>
</div>

<div class="toast position-fixed bottom-0 end-0 m-5 alert-danger " id="errorToast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
        <strong class="me-auto">Error</strong>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
        ${errorMessage}
    </div>
</div>


</body>