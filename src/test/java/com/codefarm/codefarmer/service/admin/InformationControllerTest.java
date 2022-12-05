package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.controller.admin.AdminController;
import com.codefarm.codefarmer.entity.admin.Policy;
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
public class InformationControllerTest {
    @Autowired
    private AdminController adminController;
    @Autowired
    private InformationService informationService;

    @Test
    public void policyUpdateTest(){
        Policy policy = informationService.policyShowOne(69L);

        policy.setPolicyKeyword("키워드22");
        adminController.policyUpdate(policy);
    }
}
