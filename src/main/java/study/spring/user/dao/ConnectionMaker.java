package study.spring.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    //커넥션 메이킹 메서드를 뿌림(누가 사용하는지는 모름)
    Connection makeConnection() throws ClassNotFoundException, SQLException;

}
