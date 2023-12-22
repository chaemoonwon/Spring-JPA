package jpabook.jpa.service;


import jpabook.jpa.domain.Member;
import jpabook.jpa.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@AllArgsConstructor
@RequiredArgsConstructor        //final이 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {

    // final을 쓰는 이유 : 컴파일 시점에 체크 해줄 수 있음.
    private final MemberRepository memberRepository;

//    @Autowired  //필드가 1개만 있는 경우 @Autowired 생략 가능
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }



    //회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member);      //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
         List<Member> findMembers = memberRepository.findByName(member.getName());
         if(!findMembers.isEmpty()) {
             throw new IllegalStateException("이미 존재하는 회원 입니다.");
         }

    }


    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단일 회원 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}