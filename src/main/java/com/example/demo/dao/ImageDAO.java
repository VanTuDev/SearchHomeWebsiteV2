package com.example.demo.dao;


import com.example.demo.beans.Image;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Image.class)
public interface ImageDAO extends DAO<Image>{
    @Override
    @SqlUpdate("INSERT INTO image_post(imageName, postId) " +
            " VALUES (:imageName,:postId)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Image image);


    @Override
    @SqlUpdate("")
    void update(@BindBean Image image);

    @Override
    @SqlUpdate("")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("")
    Optional<Image> getById(@Bind("id") long id);

    @SqlQuery("")
    List<Image> getAll();


    @Override
    @SqlQuery("")
    List<Image> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("")
    List<Image> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                  @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);
    @SqlQuery("select * from image_post where postId = :id" )
    List<Image> getByImageByPostId(@Bind("id") long id);
}
