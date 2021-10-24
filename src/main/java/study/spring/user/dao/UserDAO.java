package study.spring.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import study.spring.user.domain.User;

import java.sql.*;


public class UserDAO {


    //상속해서 가져오는 것이 아니니 서브 클래스는 이를 사용할 수가 없다.
    //커넥션 메이커를 사용해서 makeConnection을 호출함
    private ConnectionMaker connectionMaker;  //현재는 어떤 커넥션을 가져올지 모른다.(N커넥션? D커넥션?)
                                                // 런타임 시점에 주입되는 의존관계에 따라 정해진다.

    private Connection c;
    private User user;


    public UserDAO() {
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public UserDAO(ConnectionMaker connectionMaker) {
        CountingDaoFactory daoFactory = new CountingDaoFactory();
        this.connectionMaker = daoFactory.connectionMaker(); //이쪽이 코드가 더 간결함
      /*  AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
      this.connectionMaker = context.getBean("connectionMaker",ConnectionMaker.class);*/
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users values (?, ?, ?)"
        );

        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());



        ps.executeUpdate();

        ps.close();
        c.close();

    }



    public User get(String id) throws ClassNotFoundException, SQLException {

        c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }




    //1차 리팩토링 -> 커넥션의 분리
    //2차 리팩토링 -> 클래스의 추상화
    //3차 리팩토링 -> 커넥션의 완전 분리
    /*public Connection getConnection() throws SQLException, ClassNotFoundException;
        *//*Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:~/spring", "sa", ""
        );*//*

*/

}
