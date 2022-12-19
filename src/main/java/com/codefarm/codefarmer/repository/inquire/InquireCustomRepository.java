package com.codefarm.codefarmer.repository.inquire;

import com.codefarm.codefarmer.entity.inquire.Inquire;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquireCustomRepository {
//    검색(작성자) + 페이징
    public List<Inquire> findByInquireLikeMemberNickname(@Param("memberNickname")String memberNickname);

//    작성자 검색했을 시 개수
    public Integer countByMemberNickname(@Param("memberNickname")String memberNickname);
}
