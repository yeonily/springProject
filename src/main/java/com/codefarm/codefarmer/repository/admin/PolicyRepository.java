package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.domain.admin.Criteria;
import com.codefarm.codefarmer.entity.admin.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
//    정책번호 순으로 정렬
    @Query("select p from Policy p order by p.policyId desc")
    public List<Policy> OrderByPolicyId(Criteria criteria);

//    정책 글 개수
    @Query("select count(p) from Policy p")
    public int countByPolicy(Criteria criteria);

//    @Modifying
//    @Query("update Policy p set p.policyKeyword = :policyKeyword, p.policyTitle = :policyTitle, p.policyContent = :policyContent where p.policyId = :policyId")
//    public void policyUpdate(Policy policy);

}
