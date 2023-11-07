package com.example.demo.Service;

import com.example.demo.beans.Address;
import com.example.demo.beans.Image;
import com.example.demo.dao.AddressDAO;
import com.example.demo.dao.ImageDAO;

import java.util.List;

public class ImageService extends Service<Image, ImageDAO> implements ImageDAO{
    public ImageService() {
        super(ImageDAO.class);
    }

    @Override
    public List<Image> getByImageByPostId(long id) {
        return jdbi.withExtension(ImageDAO.class,dao -> dao.getByImageByPostId(id));
    }
}
