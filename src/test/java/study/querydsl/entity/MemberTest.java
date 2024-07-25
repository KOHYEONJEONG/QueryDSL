package study.querydsl.entity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit //이게 없으면 다시 Test 실행하면 Rollback 됨. 그럼 DB에 쌓임.
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    void testEntity() {

        Team teamA = new Team("teamA");
        Team teamB= new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10,teamA);
        Member member2 = new Member("member2", 20,teamA);

        Member member3 = new Member("member3",30,teamB);
        Member member4 = new Member("member4", 40,teamB);

        //insert into member (age,team_id,username,member_id) values (10,1,'member1',1);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //초기화
        em.flush();//영속성 컨텍스트에 있는 오브젝트를 실제 쿼리를 만들어 DB에 날린다.
        em.clear();//영속성 컨텍스트 초기화, 캐시가 날라가서 쿼리를 날려 볼때 깔끔하게 나옴.


        List<Member> members = em.createQuery("Select m from Member m", Member.class).getResultList();

        for (Member member : members){
            System.out.println("member = "+member); //member = Member(id=1, username=member1, age=10)
            System.out.println("-> member.team"+member.getTeam());//-> member.teamTeam(id=1, name=teamA)
        }
    }

}