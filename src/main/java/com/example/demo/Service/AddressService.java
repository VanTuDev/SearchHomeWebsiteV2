package com.example.demo.Service;



import com.example.demo.beans.Address;
import com.example.demo.dao.AddressDAO;

import java.util.List;

public class AddressService extends Service<Address, AddressDAO> implements AddressDAO{
    public AddressService() {
        super(AddressDAO.class);
    }


    @Override
    public List<Address> getAllProvinces() {
        return jdbi.withExtension(AddressDAO.class, AddressDAO::getAllProvinces);
    }

    @Override
    public List<Address> getDistrictsByProvince(String provinceCode) {
        return jdbi.withExtension(AddressDAO.class,dao -> dao.getDistrictsByProvince(provinceCode));
    }

    @Override
    public List<Address> getWardsByDistrict(String districtCode) {
        return jdbi.withExtension(AddressDAO.class,dao -> dao.getWardsByDistrict(districtCode));
    }

    @Override
    public String loadProvinceCode(int provinceCode) {
        return jdbi.withExtension(AddressDAO.class,dao -> dao.loadProvinceCode(provinceCode));
    }

    @Override
    public String loadDistrictCode(int districtCode) {
        return jdbi.withExtension(AddressDAO.class,dao -> dao.loadDistrictCode(districtCode));
    }

    @Override
    public String loadWardCode(int wardCode) {
        return jdbi.withExtension(AddressDAO.class,dao -> dao.loadWardCode(wardCode));
    }
}
