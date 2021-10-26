package study.spring.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.spring.user.dao.Level;


@Getter @Setter     //자바빈 프로퍼티는 롬복으로 생성한다.
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private String password;
    private Level level;
    private int login;
    private int recommend;






}
