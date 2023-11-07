package com.example.demo.dao;


import com.example.demo.beans.Post;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Post.class)
public interface PostDAO extends DAO<Post>{


    @Override
    @SqlUpdate("INSERT INTO post (" +
            "  status, imageName, title, descriptions, price, " +
            "  area, electrict, water, provinceCode, districtCode, " +
            "  wardCode, addressDetail, userId, roomTypeId, startAt, " +
            "  endAt, createAt, updateAt " +
            ") " +
            "VALUES (" +
            "  :status, :imageName, :title, :descriptions, :price, " +
            "  :area, :electrict, :water, :provinceCode, :districtCode, " +
            "  :wardCode, :addressDetail, :userId, :roomTypeId, :startAt, " +
            "  :endAt, :createAt, :updateAt " +
            ");")
    @GetGeneratedKeys("id")
    long insert(@BindBean Post post);



    @Override
    @SqlUpdate("UPDATE post " +
            "SET " +
            "  status = :status, " +
            "  imageName = :imageName, " +
            "  title = :title, " +
            "  descriptions = :descriptions, " +
            "  price = :price, " +
            "  area = :area, " +
            "  electrict = :electrict, " +
            "  water = :water, " +
            "  provinceCode = :provinceCode, " +
            "  districtCode = :districtCode, " +
            "  wardCode = :wardCode, " +
            "  addressDetail = :addressDetail, " +
            "  userId = :userId, " +
            "  roomTypeId = :roomTypeId, " +
            "  startAt = :startAt, " +
            "  endAt = :endAt, " +
            "  createAt = :createAt, " +
            "  updateAt = :updateAt " +
            "WHERE id = :id ")
    void update(@BindBean Post post);

    @Override
    @SqlUpdate("DELETE FROM post WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM post WHERE id = :id")
    Optional<Post> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM post")
    List<Post> getAll();

    @Override
    @SqlQuery(" SELECT * FROM post LIMIT :limit OFFSET :offset")
    List<Post> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT p.id, " +
            "p.status, " +
            "p.imageName, " +
            "p.title, " +
            "p.descriptions, " +
            "p.price, " +
            "p.area, " +
            "p.electrict, " +
            "p.water, " +
            "p.addressDetail, " +
            "p.userId, " +
            "p.roomTypeId, " +
            "p.startAt, " +
            "p.endAt, " +
            "p.provinceCode, " +
            "p.districtCode, " +
            "p.wardCode, " +
            "p.createAt, " +
            "prv.name AS provinceName, " +
            "dis.name AS districtName, " +
            "w.name AS wardName " +
            "FROM post p " +
            "LEFT JOIN provinces prv ON p.provinceCode = prv.code " +
            "LEFT JOIN districts dis ON p.districtCode = dis.code " +
            "LEFT JOIN wards w ON p.wardCode = w.code  WHERE p.status = 0 " +
            "ORDER BY  <orderBy>  <orderDir>  LIMIT :limit OFFSET :offset ")
    List<Post> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                              @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery(" SELECT COUNT(id) FROM post")
    int count();

    @SqlQuery(" SELECT * FROM post WHERE title LIKE CONCAT('%', :query, '%') LIMIT :limit OFFSET :offset")
    List<Post> getByQuery(@Bind("query") String query, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery(" SELECT COUNT(id) FROM post WHERE title LIKE CONCAT('%', :query, '%')")
    int countByQuery(@Bind("query") String query);

    @SqlQuery("SELECT tr.id AS type_of_room_id, p.id AS post_id, tr.typeName, p.imageName, p.title, p.descriptions, p.price, p.area, p.provinceCode, p.districtCode, p.wardCode, p.addressDetail, p.userId, p.roomTypeId, p.startAt, p.endAt, p.createAt, p.updateAt " +
            "   FROM type_of_room AS tr " +
            "   INNER JOIN post AS p ON tr.id = p.roomTypeId " +
            "   WHERE tr.id = :id " +
            "LIMIT :limit OFFSET :offset")
    List<Post> getPostByRoomType(@Bind("id") long id, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT COUNT(*) " +
            "FROM (" +
            "    SELECT tr.id AS type_of_room_id, p.id AS post_id, tr.typeName, p.imageName, p.title, p.descriptions, p.price, p.area, p.provinceCode, p.districtCode, p.wardCode, p.addressDetail, p.userId, p.roomTypeId, p.startAt, p.endAt, p.createAt, p.updateAt" +
            "    FROM type_of_room AS tr" +
            "    INNER JOIN post AS p ON tr.id = p.roomTypeId" +
            "    WHERE tr.id = :id" +
            ") AS subquery_alias;")
    int countByQueryType(@Bind("id") long id);

    @SqlQuery("SELECT post.* " +
            " FROM post " +
            " JOIN user ON post.userId = user.id " +
            " where user.id = :id " +
            "ORDER BY  CREATEAT ")
    List<Post> getAllPostByUserId(@Bind("id") long id);
    @SqlQuery("SELECT d.code AS district, p.id AS post_id, p.imageName, p.title, p.descriptions, p.price, p.area, p.provinceCode, p.districtCode, p.wardCode, p.addressDetail, p.userId, p.roomTypeId, p.startAt, p.endAt, p.createAt, p.updateAt " +
            "            FROM districts AS d " +
            "            INNER JOIN post AS p ON d.code = p.districtCode " +
            "            WHERE d.code = :districtCode  " +
            "            LIMIT :limit OFFSET :offset")
    List<Post> getPostByDistrictCode(@Bind("districtCode") String  districtCode, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT COUNT(*) FROM districts AS d INNER JOIN post AS p ON d.code = p.districtCode WHERE d.code= :districtCode")
    int countByQueryDistrictCode(@Bind("districtCode") String districtCode);
    @SqlUpdate("UPDATE  post p SET p.status = 1 WHERE id = :id")
    void updateStatus(@Bind("id") long id);
    @SqlUpdate("UPDATE  post p SET p.status = 0 WHERE id = :id")
    void show(@Bind("id") long id);

}
