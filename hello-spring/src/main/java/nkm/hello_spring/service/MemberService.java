package nkm.hello_spring.service;

import nkm.hello_spring.domain.Member;
import nkm.hello_spring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validateMemberDuplication(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberDuplication(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(x->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }
}
