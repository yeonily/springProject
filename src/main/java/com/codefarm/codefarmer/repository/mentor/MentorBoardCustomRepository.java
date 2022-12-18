package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorBoard;

import java.util.List;

public interface MentorBoardCustomRepository {
//    검색
    public List<MentorBoard> findByMentorBoardSearch(String keyword, String searchText);
//    검색했을 때 글 개수
    public Integer searchCountByMentorBoard(String keyword, String searchText);
//    관리자 화면
    public List<MentorBoard> showAdmin();
}
