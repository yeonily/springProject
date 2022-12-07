package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
//    관리자
//    검색(제목+내용) + 페이징
    public Page<Policy> findByPolicyTitleContainingOrPolicyContentContaining(String policyTitle, String policyContent, Pageable pageable);
//    검색(제목) + 페이징
    public Page<Policy> findByPolicyTitleContaining(String policyTitle, Pageable pageable);
//    검색(내용) + 페이징
    public Page<Policy> findByPolicyContentContaining(String policyContent, Pageable pageable);

//    사용자
//    검색(키워드, 제목, 내용, 키워드) + 페이징
    public Page<Policy> findByPolicyKeywordContainingOrPolicyTitleContainingOrPolicyContentContaining(String policyKeyword, String policyTitle, String policyContent, Pageable pageable);


//    정책 글 개수
    @Query("select count(p) from Policy p")
    public int countByPolicy();

//    @Transactional
//    @Modifying
//    @Query("update Policy p set p.policyKeyword = :#{#Policy.policyKeyword}, p.policyTitle = :#{#Policy.policyTitle}, p.policyContent = :#{#Policy.policyContent} where p.policyId = :#{#Policy.policyId}")
//    public void policyUpdate(@Param(value = "Policy")Policy policy);

}
