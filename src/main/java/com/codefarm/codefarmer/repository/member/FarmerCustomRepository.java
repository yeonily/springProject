package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.User;

import java.util.List;

public interface FarmerCustomRepository {
    //내가 등록한 프로그램 신청자 select
    public List<User> findMyProgramApplyers(Long memberId);

}
