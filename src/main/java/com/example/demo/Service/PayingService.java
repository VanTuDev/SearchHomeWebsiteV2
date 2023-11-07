package com.example.demo.Service;

import com.example.demo.beans.Paying;
import com.example.demo.dao.PayingDAO;

import java.util.List;
import java.util.Optional;

public class PayingService extends Service<Paying, PayingDAO> implements PayingDAO {
    public PayingService() {
        super(PayingDAO.class);
    }

    @Override
    public Optional<Paying> getDescription(String description) {
        return jdbi.withExtension(PayingDAO.class,dao ->dao.getDescription(description));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Paying> getPayingById(long id) {
        return jdbi.withExtension(PayingDAO.class,dao ->dao.getPayingById(id));
    }

    @Override
    public List<Paying> getBulkById(long id) {
        return jdbi.withExtension(PayingDAO.class,dao ->dao.getBulkById(id));
    }

    @Override
    public void insertNoReturn(Paying Paying) {
        jdbi.useExtension(PayingDAO.class, dao -> dao.insertNoReturn(Paying));
    }


}
