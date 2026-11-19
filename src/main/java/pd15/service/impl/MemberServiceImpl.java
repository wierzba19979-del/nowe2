package pd15.service.impl;

import pd15.domain.Member;
import pd15.dto.MemberDto;
import pd15.exception.MemberNotFoundException;
import pd15.repository.MemberRepository;
import pd15.service.MemberService;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDto registerMember(Member member) {
        Member saveMember = memberRepository.save(member);
        return MemberDto.of(
                saveMember.getId(),
                saveMember.getName(),
                saveMember.getEmail());
    }

    @Override
    public MemberDto findById(Long id) {
        return memberRepository.findById(id).map(member -> MemberDto.of(
                        member.getId(),
                        member.getName(),
                        member.getEmail()))
                .orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream().map(member -> MemberDto.of(
                        member.getId(),
                        member.getName(),
                        member.getEmail()))
                .toList();
    }
}
