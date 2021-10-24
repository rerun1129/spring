package study.spring.user.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {

    //3차 리팩토링 -> 관심사를 아예 DAO 클래스에서 독립 시키기
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
         Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:~/spring", "sa", ""
        );
    }
}
