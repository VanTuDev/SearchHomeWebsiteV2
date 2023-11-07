package com.example.demo.dao;

import com.example.demo.beans.RoomType;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(RoomType.class)
public interface RoomTypeDAO extends DAO<RoomType> {
    @Override
    @SqlUpdate("")
    @GetGeneratedKeys("id")
    long insert(@BindBean RoomType roomType);


    @Override
    @SqlUpdate("")
    void update(@BindBean RoomType roomType);

    @Override
    @SqlUpdate("")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM type_of_room WHERE id = :id")
    Optional<RoomType> getById(@Bind("id") long id);

    @SqlQuery("SELECT id AS id , typeName AS name FROM type_of_room")
    List<RoomType> getAll();


    @Override
    @SqlQuery("SELECT id AS id , typeName AS name FROM type_of_room LIMIT :limit OFFSET :offset")
    List<RoomType> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("")
    List<RoomType> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                  @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

}
