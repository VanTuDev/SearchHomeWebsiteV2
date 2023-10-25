<%-- 
    Document   : postContent
    Created on : Oct 25, 2023, 12:33:16 AM
    Author     : Alpha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="container">
            <div class ="row">
                <h3 class="col-12"><strong>Đăng tin cho thuê</strong></h3>
                <img src="${pageContext.request.contextPath}/img/loading22x.png" title="Loading 2">
                <div class="col-3">
                    <h4 class="title"><Strong>1. Đăng tin cho thuê phòng trọ </Strong></h4>
                    <h5 ><Strong><a href="createPost_first.jsp">Chọn lại danh mục bài đăng</a> </Strong></h5>
                    <div class="guide">
                        <table>
                            <tr>
                                <th><h5><strong>Hướng dẫn đăng tin</strong></h5></th>
                            </tr>
                            <tr>
                                <td><strong>Nội dung tốt</strong>
                                    <ul>
                                        <li>Nhập ảnh thật được nhiều người xem hơn
                                        </li>
                                        <li>Nhập đầy đủ địa chỉ nhà đất cho thuê
                                        </li>
                                        <li>Nhập đầy đủ diện tích nhà đất cho thuê
                                        </li>
                                        <li>Nhập đầy đủ giá cả, chứng nhận pháp lý
                                        </li>
                                        <li>Miêu tả những điểm nổi bật, đặc thù của bất động sản.
                                        </li>
                                    </ul>
                                    <strong>Nội dung không tốt</strong>
                                    <ul>
                                        <li>Thông tin trùng lặp với bài đã đăng</li>
                                        <li>Thiếu địa chỉ nhà đất đất cụ thể</li>
                                        <li>Thiếu diện tích cụ thể</li>
                                        <li>Thiếu giá cả, chứng nhận pháp lý cụ thể</li>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-9">
                    <div class="new-content row">
                        <!--Thêm ảnh vào-->
                        <label>Đăng ảnh:
                            <input type="file" id="id" accept="image/png,image/jpeg">
                        </label>
                        <!--Tiêu đề tin-->
                        <label>Tiêu đề<span class="red">*</span>:
                            <input type="text" id="id" name="title" placeholder="Nhập tiêu đề thật hay vào để câu khách"> 
                        </label>
                        <label>Mô tả<span class="red">*</span>:
                            <textarea name="pro_description" class="input_des pro_description" id="pro_description" placeholder="Mô tả thêm thông tin, tiện ích, qui định, giá cả..."></textarea>
                        </label>
                        <label class="col-6" for="address">Địa chỉ:
                            <select name="address">
                                <option value="1">Ngũ Hành Sơn</option>
                                <option value="2">Sơn Trà</option>
                            </select>
                        </label>
                        <label class="col-6">Huyện:
                            <select name="address">
                                <option value="1">Cẩm Lệ</option>
                                <option value="2">Liên Chiểu</option>
                            </select>
                        </label>
                        <label class="col-6">Diện tích (m&#178;)<span class="red">*</span>:
                            <input type="text" id="id" name="description" placeholder="Nhập diện tích nhà"> 
                        </label >
                        <label class="col-6">Địa chỉ chi tiết<span class="red">*</span>:
                            <input type="text" id="id" name="description" placeholder="Nhập mô tả..."> 
                        </label>
                        <label>Giá<span class="red">*</span>:
                            <input type="text" id="id" name="description" placeholder="Nhập mô tả..."> 
                        </label>
                        <p class="red">(*): Không thể bỏ trống</p>
                        <hr>
                        <Strong>Thông tin người đăng bài</Strong>
                        <label class="col-6">Họ và tên<span class="red">*</span>:
                            <input type="text" id="id" name="description" placeholder="VIết tên bạn vào đây"> 
                        </label>
                        <label class="col-6">Số di động<span class="red">*</span>:
                            <input type="text" id="id" name="description" placeholder="Nhập số di động"> 
                        </label>
                        <label>Địa chỉ:
                            <input type="text" id="id" name="description" placeholder="Nhập địa chỉ chi tiết của căn nhà"> 
                        </label>
                        <button class="submit">Cập nhật</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
