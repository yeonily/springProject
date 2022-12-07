package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PolicyTest {
    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    정책 등록
    @Test
    public void policySaveTest(){
        Policy policy = new Policy();

        policy.setPolicyKeyword("키워드");
        policy.setPolicyTitle("타이틀");
        policy.setPolicyContent("컨텐트");

        policyRepository.save(policy);
    }

//    정첵 수정
    @Test
    public void policyUpdateTest(){
        Policy policy = policyRepository.findById(69L).get();
        policy.setPolicyKeyword("키워드 수정2222222");

        policyRepository.save(policy);
    }

//    정책 삭제
    @Test
    public void policyDeleteTest(){
        policyRepository.delete(policyRepository.findById(2L).get());
    }

//    정첵 목록
    @Test
    public void policySelectAllTest(){
//        policyRepository.findAll().stream().map(policy -> policy.toString()).forEach(log::info);
//        jpaQueryFactory.select(policy).from(policy)
//                .fetch()
//                .stream().map(policy -> policy.toString()).forEach(log::info);
    }

}
