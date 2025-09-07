package nkm.hello_spring.repository;

import nkm.hello_spring.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {
    private static Long id = 0L;
    private static ConcurrentHashMap<Long, Member> members = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        member.setId(++id);
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return members.values().stream().
                filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    public void clearStore(){
         members.clear();
    }
}
