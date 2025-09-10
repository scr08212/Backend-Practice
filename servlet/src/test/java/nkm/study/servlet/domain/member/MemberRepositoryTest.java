package nkm.study.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member = new Member("hello", 20);

        Member savedMember = memberRepository.save(member);

        Member loadedMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(loadedMember);
    }

    @Test
    void findById(){
        Member member = new Member("hello", 20);
        Member savedMember = memberRepository.save(member);
        Member loadedMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(loadedMember);
    }

    @Test
    void findAll(){
        Member member = new Member("hello", 20);
        Member member2 = new Member("hello", 20);

        int size = memberRepository.findAll().size();

        Assertions.assertThat(size).isEqualTo(2);
    }
}
