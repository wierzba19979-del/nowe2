package pd15.service.impl;

import lombok.AllArgsConstructor;
import pd15.domain.Member;
import pd15.dto.MemberDto;
import pd15.exception.MemberNotFoundException;
import pd15.mapper.MemberMapper;
import pd15.repository.MemberRepository;
import pd15.service.MemberService;

import java.util.List;

@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberDto registerMember(Member member) {
        Member saveMember = memberRepository.save(member);
        return memberMapper.toDto(saveMember);
    }

    @Override
    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new MemberNotFoundException());
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDto)
                .toList();
    }
}
