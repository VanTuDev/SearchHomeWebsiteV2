package com.example.demo.Servlet.Client;

import com.example.demo.Service.PostService;
import com.example.demo.Service.WishListService;
import com.example.demo.Utils.JsonUtils;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Post;
import com.example.demo.beans.User;
import com.example.demo.dto.ErrorMessage;
import com.example.demo.dto.SuccessMessage;
import com.example.demo.dto.WishListItem;
import com.example.demo.dto.WishlistItemRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/wishList")
public class WishList extends HttpServlet {
    private final WishListService wishListService = new WishListService();
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            List<WishListItem> wishlistItems = Protector.of(() -> wishListService.getByUserId(user.getId()))
                    .get(ArrayList::new);

            for (WishListItem wishlistItem : wishlistItems) {
                wishlistItem.setPost(postService.getById(wishlistItem.getPostId()).orElseGet(Post::new));
            }

            req.setAttribute("wishlistItems", wishlistItems);
        }

        req.getRequestDispatcher("/WEB-INF/views/client/wishListView.jsp.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        Protector.of(() -> wishListService.delete(id));
        resp.sendRedirect(req.getContextPath() + "/wishlist");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WishlistItemRequest wishlistItemRequest = JsonUtils.get(req, WishlistItemRequest.class);

        String successMessage = "Đã thêm bài viết vào danh sách yêu thích thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        Runnable doneFunction = () -> JsonUtils.out(
                resp,
                new SuccessMessage(200, successMessage),
                HttpServletResponse.SC_OK);
        Runnable failFunction = () -> JsonUtils.out(
                resp,
                new ErrorMessage(404, errorMessage),
                HttpServletResponse.SC_NOT_FOUND);

        WishListItem wishlistItem = new WishListItem();
        wishlistItem.setUserId(wishlistItemRequest.getUserId());
        wishlistItem.setPostId(wishlistItemRequest.getPostId());

        Protector.of(() -> wishListService.insert(wishlistItem))
                .done(r -> doneFunction.run())
                .fail(e -> failFunction.run());
    }
}
