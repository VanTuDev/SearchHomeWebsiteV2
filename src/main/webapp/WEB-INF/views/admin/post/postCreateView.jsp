<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>


<div class="col-md-6">
    <a href="#addPostModal" class="btn btn-success" data-bs-toggle="modal">
        <i class="bi bi-plus"></i> <span>Thêm bài đăng</span>
    </a>
</div>

<div class="modal fade" id="addPostModal" tabindex="-1" aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/admin/postManager/create"
                     method="post"
                        enctype="multipart/form-data">
                <input type="hidden"
                       name="userId"
                       value="${sessionScope.currentUser.id}" >
                <div class="modal-header">
                    <h5 class="modal-title" id="addEmployeeModalLabel">Đăng bài</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="title" class="form-label">Tiêu đề <span style="color: red">(*)</span></label>
                        <input name="title"
                               type="text"
                               class="form-control"
                               id="title"
                               placeholder="Tiêu đề bài viết..." required>
                        <c:if test="${not empty requestScope.violations.titleViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.titleViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Ảnh</label>
                        <input type="file"
                               class="form-control"
                               id="image"
                               name="image"
                               accept="image/*">
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Giá thành <span style="color: red">(*)</span></label>
                        <input name="price"
                               type= "number"
                               class="form-control"
                               id="price"
                               placeholder="Nhập giá và nhấn ra ngoài để cập nhập"
                               required>
                        <c:if test="${not empty requestScope.violations.priceViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.priceViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="descriptions" class="form-label">Mô tả</label>
                        <textarea name="descriptions"
                                  class="form-control"
                                  id="descriptions"
                                  placeholder="Thêm mô tả chi tiết..."
                                  required></textarea>
                        <c:if test="${not empty requestScope.violations.descriptionViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.descriptionViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <%--                    Address from server                     --%>
                    <div class="mb-3">
                    <jsp:include page="./addressView.jsp"/>
                    </div>
                    <div class="mb-3">
                        <label for="area" class="form-label">Diện tích - đơn vị(m2)<span style="color: red">(*)</span></label>
                        <textarea name="area"
                                  type="number"
                                  class="form-control"
                                  id="area"
                                  placeholder="Diện tích"
                                  required></textarea>
                        <c:if test="${not empty requestScope.violations.passwordViolations}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.passwordViolations}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="addressDetail" class="form-label">Địa chỉ chi tiết <span style="color: red">(*)</span></label>
                        <textarea name="addressDetail"
                                  class="form-control"
                                  id="addressDetail"
                                  placeholder="Thêm địa chỉ tiết"
                                  required></textarea>
                        <c:if test="${not empty requestScope.violations.addressDetail}">
                            <div class="invalid-feedback">
                                <ul class="list-unstyled">
                                    <c:forEach var="violation" items="${requestScope.violations.addressDetail}">
                                        <li>${violation}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="roomType" class="form-label">Loại</label>
                        <select name="roomType"
                                class="form-select"
                                id="roomType">
                            <c:forEach items="${requestScope.roomType}" var="type">
                                <option value="${type.id}">
                                            ${type.name}
                                </option>
                            </c:forEach>
                            <c:if test="${not empty requestScope.violations.roomTypeViolations}">
                                <div class="invalid-feedback">
                                    <ul class="list-unstyled">
                                        <c:forEach var="violation" items="${requestScope.violations.roomTypeViolations}">
                                            <li>${violation}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </select>
                    </div>
                    <div class="mb-3">
                            <label for="input_from">Ngày bắt đầu </label>
                            <input type = "text"
                                   name = "startAt"
                                   class= "form-control"
                                   id   = "input_from"
                                   placeholder = "Ngày bắt đầu">
                    </div>
                    <div class="mb-3">
                            <label for="input_to">Ngày kết thúc</label>
                            <input type = "text"
                                   name = "endAt"
                                   class= "form-control"
                                   id   = "input_to"
                                   placeholder ="Ngày kết thúc">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                    <button type="submit" class="btn btn-success">Đăng</button>
                </div>

            </form>
        </div>
    </div>
</div>



