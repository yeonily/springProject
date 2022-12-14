package com.codefarm.codefarmer.service.join;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;

    //닉네임 중복체크
    public Integer checkUserNick(String nickname) {return memberRepository.duplicateNick(nickname);}

    //회원가입 시 사용(세션저장용)
    public Long selectId(String oauthId){return memberRepository.selectMemberId(oauthId);}
    public String selectType(String oauthId){return memberRepository.selectMemberType(oauthId);}

    //디비에 oauthid있는지 검사(있으면 1 리턴)
    public Integer checkOauth(String oauthId){return memberRepository.duplicateOauth(oauthId);}
}
