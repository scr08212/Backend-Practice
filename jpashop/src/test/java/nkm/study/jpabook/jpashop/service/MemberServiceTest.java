package nkm.study.jpabook.jpashop.service;

import nkm.study.jpabook.jpashop.domain.Member;
import nkm.study.jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외(){
        Member member = new Member();
        member.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }
}