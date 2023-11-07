package com.example.demo.Service;


import com.example.demo.beans.RoomType;
import com.example.demo.dao.RoomTypeDAO;

public class RoomTypeService extends Service<RoomType, RoomTypeDAO> implements RoomTypeDAO{
    public RoomTypeService() {
        super(RoomTypeDAO.class);
    }

}
