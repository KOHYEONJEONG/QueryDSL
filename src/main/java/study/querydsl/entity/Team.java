package study.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor //JPA는 기본생성자 무조건 만들어야 에러 안남.
@ToString(of = {"id","name"})//members는 넣지 말자(연관관계는 넣지 말자)
public class Team {

    @Id @GeneratedValue
    private Long id;
    private String name;

    //연관관계 내용은 JPA 기본편 강의를 듣자.
    //양방향 연관관계이기에 @OneToMany 넣어주기
    @OneToMany(mappedBy = "team") //Member가 @ManyToOne이기에 Team은 반대인 @OneToMany로 해주면된다.(연관관계 맺기)
            //mappedBy 란 연관관계 주인이 아니다
    List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }


}
