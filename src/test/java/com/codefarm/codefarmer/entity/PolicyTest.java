package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PolicyTest {
    @Autowired
    private PolicyRepository policyRepository;

    @Test
    public void policySaveTest(){
        Policy policy = new Policy();

        policy.setPolicyKeyword("키워드");
        policy.setPolicyTitle("타이틀");
        policy.setPolicyContent("컨텐트");

        policyRepository.save(policy);
    }

    @Test
    public void policyUpdateTest(){
        Policy policy = policyRepository.findById(2L).get();
        policy.setPolicyKeyword("키워드 수정");
        policyRepository.save(policy);
    }

    @Test
    public void policyDeleteTest(){
        policyRepository.delete(policyRepository.findById(2L).get());
    }

    @Test
    public void policySelectAllTest(){
        policyRepository.findAll().stream().map(policy -> policy.toString()).forEach(log::info);
    }

}
