package study.spring.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDAO dao3 = context.getBean("userDAO", UserDAO.class); //name 은 UserDAO 에 등록된 빈의 메서드 이름
        UserDAO dao4 = context.getBean("userDAO", UserDAO.class); //name 은 UserDAO 에 등록된 빈의 메서드 이름

        System.out.println("dao3 = " + dao3);
        System.out.println("dao4 = " + dao4);

    }

}
