package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;

import java.util.List;

public interface ProgramCustomRepository {
//    검색
    public List<Program> findByProgramSearch(String keyword, String searchText);
//    검색했을 때 글 개수
    public Integer countByProgramSearch(String keyword, String searchText);
    //    관리자 화면
    public List<Program> showAdmin();
}
