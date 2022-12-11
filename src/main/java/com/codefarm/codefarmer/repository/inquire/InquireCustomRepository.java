package com.codefarm.codefarmer.repository.inquire;

import com.codefarm.codefarmer.entity.inquire.Inquire;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InquireCustomRepository {
//    검색(작성자) + 페이징
    public List<Inquire> findByInquireLikeMemberNickname(String memberNickname, Pageable pageable);

//    작성자 검색했을 시 개수
    public Integer countByMemberNickname(String memberNickname);
}
