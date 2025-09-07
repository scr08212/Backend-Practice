package nkm.hello_spring.repository;

import nkm.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void AfterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void findById(){
        Member m1 = new Member();
        m1.setName("user1");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("user2");
        memberRepository.save(m2);

        Member result = memberRepository.findById(m1.getId()).get();
        Assertions.assertThat(result).isEqualTo(m1);
    }

    @Test
    public void findByName(){
        Member m1 = new Member();
        m1.setName("user1");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("user2");
        memberRepository.save(m2);

        Member result = memberRepository.findByName(m1.getName()).get();
        Assertions.assertThat(result).isEqualTo(m1);
    }

    @Test
    public void findAll(){
        Member m1 = new Member();
        m1.setName("user1");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("user2");
        memberRepository.save(m2);

        List<Member> result = memberRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
