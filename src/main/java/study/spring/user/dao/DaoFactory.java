package study.spring.user.dao;

//DAO 의 커넥션을 뿌림(누가 사용하는지는 모름)
//DAO의 커넥션을 만드는 것을 위임받음


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    /*@Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(connectionMaker());
    }*/
}
