<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<div id="wrapper" class="wrapper">
    <header class="header">
        <div class="search-logo">
            <div class="container row">
                <div class="col-2 logopic">
                    <div class="box_logo header-content">
                        <a title="Search House, cho thuê nhà trọ, căn hộ Đà Nẵng" href="${pageContext.request.contextPath}/homeView" class="logo">
                            <img src="${pageContext.request.contextPath}/img/logo2x.png" alt="Search Home logo" width="200" height="100" title="Search Home Logo">
                        </a>
                    </div>
                </div>
                <div class="col-6 search_form_key">
                    <div class="itop_search_txt">
                        <div class="itop_search_content">
                            <form method="get" action="${pageContext.request.contextPath}/search">
                                <div class="itop_search_key">
                                    <input class="its_type_val" name="type" type="hidden" value="1">
                                    <input class="its_key" id="textSearch" name="q" placeholder="Nhập khu vực, địa điểm, dụ án bạn muốn thuê" type="text" autocomplete="off">
                                </div>
                                <button type="submit" class="its_submit"><i
                                        class="fa fa-search" aria-hidden="true"></i> Tìm kiếm
                                </button>
                            </form>
                        </div>
                        <div class="search_auto_ct content_hide_out"></div>
                    </div>
                </div>
                <div class="col-3 header_user">
                    </div>
                    <c:choose>
                        <c:when test="${not empty sessionScope.currentUser}">
                            <span class="header_user_nologin">
                            <strong>
                            <a href="${pageContext.request.contextPath}/client/settingView">
                                ${sessionScope.currentUser.fullname}
                             </a>
                            </strong>
                            </span>
                            <div class="row">
                                <c:choose>
                                    <c:when test="${sessionScope.currentUser.valid == 101}">
                                        Hãy xác thực thông tin để được đăng bài
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/createPostFromUser">Đăng tin</a>
                                    </c:otherwise>
                                </c:choose>
                                 <a class="header_user_nologin" href="${pageContext.request.contextPath}/signout" role="button">
                                    <i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
                                  </a>
                                 </div>
                        </c:when>
                        <c:otherwise>
                            <!-- Đăng nhập và Đăng ký nút -->
                            <a class="header_user_nologin" href="${pageContext.request.contextPath}/signup" role="button">
                                <i class="fa-solid fa-user-plus"></i> Đăng ký
                            </a>
                            <a class="header_user_nologin" href="${pageContext.request.contextPath}/signin" role="button">
                                <i class="fa-solid fa-right-to-bracket"></i> Đăng nhập
                            </a>
                        </c:otherwise>
                    </c:choose>


                </div>
                <div class="clear"></div>
            </div>
        </div>
<%--        <div class="content header_menu">--%>
<%--            <div class="container header_content">--%>
<%--                <ul class="header_menu_ul">--%>
<%--                    <li class="header_menu_li active"><a class="header_menu_a" href="${pageContext.request.contextPath}/homeView">Trang chủ</a></li>--%>
<%--                    <li data="10" class="header_menu_li header_menu_cat header_menu_li_1 "><a--%>
<%--                            class="header_menu_a header_menu_a_cat" href="/cho-thue-phong-tro-cat10.htm">Phòng trọ</a>--%>
<%--                    </li>--%>
<%--                    <li data="13" class="header_menu_li header_menu_cat header_menu_li_2 "><a--%>
<%--                            class="header_menu_a header_menu_a_cat" href="/cho-thue-chung-cu-mini-cat13.htm">Chung cư--%>
<%--                        mini</a></li>--%>
<%--                    <li data="1" class="header_menu_li header_menu_cat header_menu_li_3 "><a--%>
<%--                            class="header_menu_a header_menu_a_cat" href="/cho-thue-can-ho-chung-cu-cat1.htm">Căn hộ--%>
<%--                        chung cư</a></li>--%>
<%--                    <li data="14" class="header_menu_li header_menu_cat header_menu_li_4 "><a--%>
<%--                            class="header_menu_a header_menu_a_cat" href="/cho-thue-o-ghep-cat14.htm">Ở ghép</a></li>--%>
<%--                    <div class="clear"></div>--%>
<%--                </ul>--%>
<%--                <div class="clear"></div>--%>
<%--            </div>--%>
<%--            <div class="clear"></div>--%>
<%--        </div>--%>

        <div class="clear"></div>
    </header>

    <div id="fb-root"></div>
</div>
<br>
<br>
<br>
<br>


