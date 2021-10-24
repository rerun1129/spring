package study.spring.user;

import lombok.extern.slf4j.Slf4j;
import study.spring.user.dao.ConnectionMaker;
import study.spring.user.dao.DConnectionMaker;
import study.spring.user.dao.DaoFactory;
import study.spring.user.dao.UserDAO;
import study.spring.user.domain.User;

import java.sql.SQLException;


@Slf4j
public class TestMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        //커넥션을 받아서 사용(근데 지금까지 무슨일이 있었는지는 모름. 그냥 씀)
        UserDAO dao = new DaoFactory().userDAO();


        //add 검증
        User user = new User();
        user.setId("1");
        user.setName("고윤석");
        user.setPassword("hi");

        dao.add(user);

        log.info("등록 성공={}",user.getId());



        //get 검증
        User user2 = dao.get(user.getId());

        log.info("유저 이름={}",user2.getName());
        log.info("유저 비밀번호={}",user2.getPassword());

        log.info("조회 성공={}",user2.getId());

    }
}