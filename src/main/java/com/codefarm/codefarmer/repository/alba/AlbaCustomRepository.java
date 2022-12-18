package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.entity.alba.Alba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbaCustomRepository {
    public List<Alba> findByLatest();
    // 최신순
    public Page<AlbaDTO> findByAlbaNewList(Pageable pageable);
    // 시급순
    public Page<AlbaDTO> findByAlbaPayList(Pageable pageable);
    // 마감순
    public Page<AlbaDTO> findByAlbaEndList(Pageable pageable);
    // 검색(작성자) + 페이징
    public List<Alba> findByAlbaLikeMemberName(String memberNickname);
    // 작성자 검색했을 시 개수
    public Integer countByMemberName(String memberNickname);
    // 관리자 화면
    public List<Alba> showAdmin();
}
