package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
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
public class AlbaDetailServiceTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private AlbaDetailService albaDetailService;

    @Test
    public void showAllTest(){
        albaDetailService.showAll().stream().map(AlbaDTO::getAlbaAddress).forEach(log::info);
    }

    // 게시글 삭제
    @Test
    public void removeAlbaIdTest(){
        albaDetailService.removeAlbaId(96L);
    }

//    @Test
//    public void showByAlbaIdTest(){
//        albaDetailService.showByAlbaId();
//    }
}
