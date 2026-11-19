package pd15.service;

import pd15.domain.Member;
import pd15.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto registerMember(Member member);

    MemberDto findById(Long id);

    List<MemberDto> findAll();
}
