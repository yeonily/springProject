package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.MemberAlba;

import java.util.List;

public interface MemberAlbaCustomRepository {
//    검색
    public List<MemberAlba> findByMemberAlba (String keyword, String searchText);
//    검색했을 때 글 수
    public Integer countByMemberAlbaSearch (String keyword, String searchText);
}
