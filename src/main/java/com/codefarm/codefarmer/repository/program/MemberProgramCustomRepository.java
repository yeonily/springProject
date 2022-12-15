package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.program.MemberProgram;

import java.util.List;

public interface MemberProgramCustomRepository {
//    검색
    public List<MemberProgram> findByMemberProgram (String keyword, String searchText);
//    검색했을 때 글 개수
    public Integer countByMemeberProgramSearch(String keyword, String searchText);
}
