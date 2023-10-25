package com.SearchHome_V2.Service;

import com.SearchHome_V2.beans.Address;
import com.SearchHome_V2.dao.AddressDAO;

import java.util.List;

public class AddressService extends Service<Address, AddressDAO> implements AddressDAO{
    public AddressService() {
        super(AddressDAO.class);
    }


    @Override
    public List<Address> getDistrictCodeAndProvincesCode() {
        return null;
    }

    @Override
    public List<Address> getDistrictNameAndProvincesName() {
        return null;
    }
}
