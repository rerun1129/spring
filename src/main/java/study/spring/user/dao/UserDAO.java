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
    private PreparedStatement ps;

    private JdbcContext jdbcContext;

    public UserDAO() {
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public UserDAO(ConnectionMaker connectionMaker) {
        DaoFactory daoFactory = new DaoFactory();
        this.connectionMaker = daoFactory.connectionMaker(); //이쪽이 코드가 더 간결함
        this.jdbcContext = new JdbcContext(connectionMaker);
      /*  AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
      this.connectionMaker = context.getBean("connectionMaker",ConnectionMaker.class);*/
    }

    public void add(final User user) throws ClassNotFoundException, SQLException {
        StatementStrategy strategy = c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values (?, ?, ?)");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            return ps;
        };
        this.jdbcContext.workWithStatementStrategy(strategy);       //workWithStatementStrategy 이 템플릿이고 strategy 가 콜백의 역할을 한다. 콜백 메서드는 호출되어 참조변수에 값을 담아주고 담긴 값을 템플릿에 전달한다.
                                                                    //매개변수에 메서드를 담아줄수 있기에 이를 함수형 인터페이스라고 하기도 하며 보통 자바스크립트와 같은 프론트 언어에서 하는 것처럼 메서드를 변수 하나에 담을 수 있게 해준다.
    }


    public User get(String id) throws ClassNotFoundException, SQLException {

        c = connectionMaker.makeConnection();

        ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public void delete() throws SQLException, ClassNotFoundException {
        jdbcContext.executeSql("delete from users");
    }




    public int getCount() throws SQLException, ClassNotFoundException {

        ResultSet rs = null;

        int count = 0;

        try {
            c = connectionMaker.makeConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();

            rs.next();

            count = rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
        return count;
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
