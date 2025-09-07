package nkm.hello_spring.service;

import nkm.hello_spring.domain.Member;
import nkm.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long id = memberService.join(member);
        // then
        var result = memberService.findById(id).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void checkDuplicatedMember(){
        Member member = new Member();
        member.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}