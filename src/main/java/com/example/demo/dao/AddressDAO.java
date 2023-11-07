package com.example.demo.dao;


import com.example.demo.beans.Address;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Address.class)
public interface AddressDAO extends DAO<Address> {
    @SqlUpdate("INSERT INTO address (province_code, province_name, district_code, district_name, wards_code, wards_name) " +
            "VALUES (:provinceCode, :provinceName, :districtCode, :districtName, :wardsCode, :wardsName)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Address address);

    @SqlUpdate("UPDATE address SET province_code = :provinceCode, province_name = :provinceName, " +
            "district_code = :districtCode, district_name = :districtName, " +
            "wards_code = :wardsCode, wards_name = :wardsName WHERE id = :id")
    void update(@BindBean Address address);

    @SqlUpdate("DELETE FROM address WHERE id = :id")
    void delete(@Bind("id") long id);

    @SqlQuery("SELECT * FROM address WHERE id = :id")
    Optional<Address> getById(@Bind("id") long id);

    @SqlQuery("SELECT provinces.code AS provinceCode, provinces.name AS provinceName, " +
            "districts.code AS districtCode, districts.name AS districtName, " +
            "wards.code AS wardsCode, wards.name AS wardsName " +
            "FROM provinces " +
            "INNER JOIN districts ON districts.province_code = provinces.code " +
            "INNER JOIN wards ON wards.district_code = districts.code " +
            "WHERE provinces.name = 'Đà Nẵng' " +
            "ORDER BY wards.name DESC")
    List<Address> getAll();
    @SqlQuery("SELECT * FROM address LIMIT :limit OFFSET :offset")
    List<Address> getPart(@Bind("limit") int limit, @Bind("offset") int offset);
    @Override
    @SqlQuery("")
    List<Address> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                 @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT provinces.code AS provinceCode, provinces.name AS provinceName" +
            "            FROM provinces " +
            "            WHERE provinces.code = 48")
    List<Address> getAllProvinces();


    @SqlQuery("SELECT DISTINCT districts.code AS districtCode, districts.name AS districtName " +
            "FROM districts " +
            "WHERE province_code = :provinceCode " +
            "ORDER BY districtName")
    List<Address> getDistrictsByProvince(@Bind("provinceCode") String provinceCode);

    @SqlQuery("SELECT wards.code AS wardsCode, wards.name AS wardsName " +
            "FROM wards " +
            "WHERE district_code = :districtCode " +
            "ORDER BY wardsName")
    List<Address> getWardsByDistrict(@Bind("districtCode") String districtCode);

    @SqlQuery("SELECT name as provinceName FROM provinces where code = :provinceCode ")
    String loadProvinceCode(@Bind("provinceCode") int provinceCode);

    @SqlQuery("SELECT name as districtName FROM districts where code = :districtCode ")
    String loadDistrictCode(@Bind("districtCode") int districtCode);

    @SqlQuery("SELECT name as wardsName FROM wards where code = :wardCode ")
    String loadWardCode(@Bind("wardCode") int wardCode);


}
