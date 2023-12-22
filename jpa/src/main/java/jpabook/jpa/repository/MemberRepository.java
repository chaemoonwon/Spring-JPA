package jpabook.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabook.jpa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext    //스프링이 생성한 EntityManager에 주입
    //EntityManager는 @PersistenceContext어노테이션으로 주입이 가능한데,
    //스프링 부트에서는 @Autowired도 지원을 해줌.
    private final EntityManager em;

//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    //ManagerFactory를 직접 주입 받을 수 있음
//    @PersistenceUnit
//    private EntityManagerFactory em;

    public void save(Member member) {
        em.persist(member);
    }

    // 단일 조회
    public Member findOne(Long id) {
         return em.find(Member.class , id);
    }


    // 전체 조회
    public List<Member> findAll() {
        // JPQL 쿼리
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 회원 이름을 바인딩 해서 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
