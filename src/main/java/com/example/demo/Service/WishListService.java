package com.example.demo.Service;

import com.example.demo.dao.WishListItemDAO;
import com.example.demo.dto.WishListItem;

import java.util.List;

public class WishListService extends  Service<WishListItem, WishListItemDAO> implements WishListItemDAO {
    public WishListService() {
        super(WishListItemDAO.class);
    }

    @Override
    public List<WishListItem> getByUserId(long userId) {
        return jdbi.withExtension(WishListItemDAO.class,dao -> dao.getByUserId(userId));
    }

    @Override
    public int countByUserIdAndProductId(long userId, long postId) {
        return jdbi.withExtension(WishListItemDAO.class,dao -> dao.countByUserIdAndProductId(userId,postId));
    }
}
