package jpabook.jpa.service;

import jakarta.persistence.EntityManager;
import jpabook.jpa.domain.Member;
import jpabook.jpa.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception {
        //given 주어졌을 때
        Member member = new Member();
        member.setName("moon");

        //when 실행하면
        Long saveId = memberService.join(member);

        //then 결과가 나온다.
        Assert.assertEquals(member, memberRepository.findOne(saveId));

    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);    //예외가 발생해야 합니다.


        //then
        fail("에러가 발생해야 한다.");

    }

}