package study.querydsl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor //JPA는 기본생성자 무조건 만들어야 에러 안남.
@ToString(of = {"id","username","age"}) // team은 넣으면 안됨. 필드만, 연관관계는 넣지 말자.
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //다대일 관계를 정의할 때 사용
    @JoinColumn(name = "team_id") //JoinColumn: Member와 Team간의 매칭, 컬럼명
    private Team team; //Member가 Team을 참조하는 N(1) 관계

    public Member(String username){
        this(username,0);
    }

    public Member(String username, int age) {
      this(username,age,null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); //Team의 members List에 Member를 추가
    }



}
