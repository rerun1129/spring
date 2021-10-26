package study.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring.user.dao.DaoFactory;
import study.spring.user.dao.Level;
import study.spring.user.dao.UserDAO;
import study.spring.user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ApplicationTests {

	@Autowired
	UserDAO dao;

	User user;
	User user2;
	User user3;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void init() throws SQLException, ClassNotFoundException {
		dao.delete();

		this.user = new User("1", "고윤석", "password1", Level.BASIC, 1, 0);
		this.user2 = new User("2", "김영한", "password2", Level.SILVER, 55, 10);
		this.user3 = new User("3", "이일민", "password3", Level.GOLD, 100, 40);



	}
	
	@Test
	public void addAndGet() throws Exception{

		//given


		dao.add(user);

		//when
		User findUser = dao.get(user.getId());

		//then

		assertThat(findUser.getName()).isEqualTo(user.getName());
		assertThat(findUser.getPassword()).isEqualTo(user.getPassword());



	}


	@Test
	public void deleteAndCount() throws Exception{




		dao.add(user);
		dao.add(user2);

		int count = dao.getCount();

		assertThat(count).isEqualTo(2);

		dao.delete();

		int recount = dao.getCount();

		assertThat(recount).isEqualTo(0);

	}

	@Test
	public void update() throws Exception{

		dao.add(user);
		dao.add(user2);


		user.setName("오민규");
		user.setPassword("password6");
		user.setLevel(Level.GOLD);
		user.setLogin(1000);
		user.setRecommend(200);

		dao.update(user);

		User findUser = dao.get(this.user.getId());
		assertThat(findUser.getName()).isEqualTo("오민규");
		assertThat(user2.getName()).isEqualTo("김영한");


	}

}
