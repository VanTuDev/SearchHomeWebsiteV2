package com.example.demo.Service;



import com.example.demo.beans.User;
import com.example.demo.dao.UserDAO;

import java.util.List;
import java.util.Optional;

public class UserService extends Service<User, UserDAO> implements UserDAO{
    public UserService() {
        super(UserDAO.class);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getByUsername(username));
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        jdbi.useExtension(UserDAO.class, dao -> dao.changePassword(userId, newPassword));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getByEmail(email));

    }

    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getByPhoneNumber(phoneNumber));
    }

    @Override
    public int count() {
        return jdbi.withExtension(UserDAO.class, UserDAO::count);
    }

    @Override
    public Optional<User> getByUserId(long id) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getByUserId(id));
    }

    @Override
    public Optional<User> getUserIdVer2(long id) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getUserIdVer2(id));
    }

    @Override
    public List<User> getFilteredAndOrderedUsers(String role, int valid, String orderBy, String orderDir, int limit, int offset) {
        return  jdbi.withExtension(UserDAO.class,dao -> dao.getFilteredAndOrderedUsers(role,valid,orderBy,orderDir,limit,offset));

    }

    @Override
    public List<User> getAllByConditionsAndValid(String role, int valid) {
        return jdbi.withExtension(UserDAO.class, dao -> dao.getAllByConditionsAndValid(role,valid));
    }

    @Override
    public void hide(long id) {
        jdbi.useExtension(UserDAO.class, dao -> dao.hide(id));
    }

    @Override
    public void show(long id) {
        jdbi.useExtension(UserDAO.class, dao -> dao.show(id));
    }
}
