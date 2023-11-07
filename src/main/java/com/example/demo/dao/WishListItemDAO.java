package com.example.demo.dao;

import com.example.demo.dto.WishListItem;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(WishListItem.class)
public interface WishListItemDAO extends DAO<WishListItem>{
    @Override
    @SqlUpdate("INSERT INTO wish_list VALUES (default, :userId, :postId, NOW())")
    @GetGeneratedKeys("id")
    long insert(@BindBean WishListItem wishlistItem);

    @Override
    @SqlUpdate("UPDATE wish_list SET userId = :userId, productId = :postId, createdAt = :createdAt WHERE id = :id")
    void update(@BindBean WishListItem wishlistItem);

    @Override
    @SqlUpdate("DELETE FROM wish_list WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM wish_list WHERE id = :id")
    Optional<WishListItem> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM wish_list")
    List<WishListItem> getAll();

    @Override
    @SqlQuery("SELECT * FROM wish_list LIMIT :limit OFFSET :offset")
    List<WishListItem> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM wish_list ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<WishListItem> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                      @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT * FROM wish_list WHERE userId = :userId")
    List<WishListItem> getByUserId(@Bind("userId") long userId);

    @SqlQuery("SELECT COUNT(id) FROM wish_list WHERE userId = :userId AND productId = :postId")
    int countByUserIdAndProductId(@Bind("userId") long userId, @Bind("postId") long postId);

}
