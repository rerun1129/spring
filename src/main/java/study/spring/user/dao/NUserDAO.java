package study.spring.user.dao;

import study.spring.user.domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDAO extends UserDAO{

    public NUserDAO() {
        super();
    }

    @Override
    public void add(User user) throws ClassNotFoundException, SQLException {
        super.add(user);
    }

    @Override
    public User get(String id) throws ClassNotFoundException, SQLException {
        return super.get(id);
    }




}
