package com.SearchHome_V2.dao;

import com.SearchHome_V2.beans.Address;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Address.class)
public interface AddressDAO extends DAO<Address>{

    @SqlQuery(" SELECT districts.code, wards.code " +
            "FROM provinces provinces " +
            "INNER JOIN districts districts ON districts.province_code = provinces.code " +
            "INNER JOIN wards wards ON wards.district_code = districts.code " +
            "WHERE provinces.name = 'Đà nẵng' ORDER BY wards.name = \"DESC\" ")
    List<Address> getDistrictCodeAndProvincesCode();
    @SqlQuery("SELECT provinces.code,provinces.name,districts.code, districts.name,wards.code,wards.name " +
            "FROM provinces provinces " +
            "INNER JOIN districts districts ON districts.province_code = provinces.code " +
            "INNER JOIN wards wards ON wards.district_code = districts.code " +
            "WHERE provinces.name = 'Đà nẵng ORDER BY wards.name = \"DESC\" ")
    List<Address> getAll();
    @SqlQuery("SELECT  districts.name,wards.name " +
            "FROM provinces provinces " +
            "INNER JOIN districts districts ON districts.province_code = provinces.code " +
            "INNER JOIN wards wards ON wards.district_code = districts.code " +
            "WHERE provinces.name = 'Đà nẵng ORDER BY wards.name = \"DESC\" ")
    List<Address> getDistrictNameAndProvincesName();


}
