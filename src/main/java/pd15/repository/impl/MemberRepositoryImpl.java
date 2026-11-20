package pd15.repository.impl;

import pd15.domain.Member;
import pd15.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();

    @Override
    public Member save(Member member) {
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public List<Member> findAll() {
        return members.values().stream().toList();
    }
}
