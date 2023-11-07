<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<html>
<head>
    <title></title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            function populateDropdown($dropdown, data, valueKey, textKey) {
                console.log(data)
                $dropdown.empty();
                $dropdown.append($('<option>').val('').text(''));
                $.each(data, function(key, value) {
                    $dropdown.append($('<option>').val(value[valueKey]).text(value[textKey]));
                });
            }

            // Function to load data using AJAX and populate a dropdown
            function loadDataAndPopulateDropdown($dropdown, action, data = {}, valueKey, textKey) {
                $.ajax({
                    type: 'GET',
                    url: '${pageContext.request.contextPath}/addressServlet',
                    data: { action: action, ...data },
                    success: function(response) {
                        populateDropdown($dropdown, response, valueKey, textKey);
                    },
                    error: function(xhr, status, error) {
                        console.error(error);
                    }
                });
            }

            // Load provinces when the page loads
            loadDataAndPopulateDropdown($('#province'), 'getProvinces', {}, 'provinceCode', 'provinceName');

            $('#province').on('change', function() {
                var selectedProvinceCode = $(this).val();
                if (selectedProvinceCode) {
                    loadDataAndPopulateDropdown($('#district'), 'getDistricts', { provinceCode: selectedProvinceCode }, 'districtCode', 'districtName');

                } else {
                    $('#district').empty();
                    $('#ward').empty();
                }
            });

            $('#district').on('change', function() {
                var selectedDistrictCode = $(this).val();
                $('#districtCodeInput').val(selectedDistrictCode);
                if (selectedDistrictCode) {
                    loadDataAndPopulateDropdown($('#ward'), 'getWards', { districtCode: selectedDistrictCode }, 'wardsCode', 'wardsName');
                } else {
                    $('#ward').empty();
                }
            });

            $('#ward').on('change', function() {
                var selectedWardsCode = $(this).val();
                $('#wardsCodeInput').val(selectedWardsCode);
            });

        });
    </script>
</head>
<body>
<div class="item row">
    <input type="hidden" name="districtCode" id="districtCodeInput" value="">
    <input type="hidden" name="wardsCode" id="wardsCodeInput" value="">
    <div class="row item_sub item_sub_first">
        <label class="col-2" for="">Chọn thành phố</label>
        <div class=" col-9 item_input">
            <select class="input_select pro_city_id" id="province" name="province">
            </select>
            <c:if test="${not empty requestScope.violations.provinceViolations}">
                <ul class="list-unstyled">
                    <c:forEach var="violation" items="${requestScope.violations.provinceViolations}">
                        <li style="color: red">${violation}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>


    </div>
    <div class="row item_sub item_sub_first">
        <label class="col-2">Phường</label>
        <div class="col-9 item_input">
            <select class="input_select pro_city_id" id="district" name="district">
            </select>
            <c:if test="${not empty requestScope.violations.districtViolations}">
                <ul class="list-unstyled">
                    <c:forEach var="violation" items="${requestScope.violations.districtViolations}">
                        <li style="color: red">${violation}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>

    </div>
    <div class="row item_sub item_sub_last">
        <label class="col-2">Quận</label>
        <div class="col-9 item_input">
            <select class="input_select pro_city_id" id="ward" name="district" >
            </select>
            <c:if test="${not empty requestScope.violations.wardViolations}">
                <ul class="list-unstyled">
                    <c:forEach var="violation" items="${requestScope.violations.wardViolations}">
                        <li style="color: red">${violation}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>

    </div>



</div>
</body>
</html>
