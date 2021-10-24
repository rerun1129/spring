package study.spring.user.domain;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter     //자바빈 프로퍼티는 롬복으로 생성한다.
public class User {

    private String id;
    private String name;
    private String password;


}
