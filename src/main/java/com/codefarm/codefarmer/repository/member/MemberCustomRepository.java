package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Member;

import java.util.List;

public interface MemberCustomRepository {
    //닉네임 중복검사
    public Integer checkNick(String nickname);
    //내가 등록한 프로그램 신청자 select
    public List<Member> findMyProgramApplyers(Long memberId);
    //oauthid 검사
    public Integer checkOauth(String oauthId);



}
