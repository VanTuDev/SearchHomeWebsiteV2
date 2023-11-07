    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="vi_VN"/>
    <html>
    <head>
      <title></title>
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
    <div class="row">
        <input type="hidden" name="districtCode" id="districtCodeInput" value="">
        <input type="hidden" name="wardsCode" id="wardsCodeInput" value="">
        <div class="col-md-4">
          <label for="province" class="form-label">Chọn Thành Phố</label>
          <select class="form-select" id="province" name="province">
          </select>
        </div>
      <div class="col-md-4">
          <label for="district" class="form-label">Chọn quận</label>
          <select class="form-select" id="district" name="district">
          </select>
      </div>
      <div class="col-md-4">
          <label for="ward" class="form-label">Chọn phường</label>
          <select class="form-select" id="ward" name="ward">
          </select>
      </div>
    </div>
    </body>
    </html>
