package study.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring.user.dao.DaoFactory;
import study.spring.user.dao.UserDAO;
import study.spring.user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ApplicationTests {

	@Autowired
	UserDAO dao;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void init() throws SQLException, ClassNotFoundException {
		dao.delete();
	}
	
	@Test
	public void addAndGet() throws Exception{

		//given
		User user = new User("1","고윤석","hi");

		dao.add(user);

		//when
		User findUser = dao.get(user.getId());

		//then

		assertThat(findUser.getName()).isEqualTo(user.getName());
		assertThat(findUser.getPassword()).isEqualTo(user.getPassword());



	}


	@Test
	public void deleteAndCount() throws Exception{

		User user1 = new User("1","고윤석","hi");
		User user2 = new User("2","김영한","bye");


		dao.add(user1);
		dao.add(user2);

		int count = dao.getCount();

		assertThat(count).isEqualTo(2);

		dao.delete();

		int recount = dao.getCount();

		assertThat(recount).isEqualTo(0);

	}

}
