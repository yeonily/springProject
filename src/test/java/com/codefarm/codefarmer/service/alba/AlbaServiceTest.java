package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class AlbaServiceTest {
    @Autowired
    private AlbaService albaService;
    @Autowired
    private AlbaListService albaListService;

    //메인에서 알바리스트 뽑는 메소드 테스트
    @Test
    public void showAlbaListTest() {
        albaService.showAlbaList1().forEach(alba -> {
            log.info(alba.getAlbaId() + "");
            log.info(alba.getAlbaTitle() + "");
            log.info(alba.getAlbaApplyCount() + "");
            log.info(alba.getAlbaApplyTotalCount() + "");
            log.info(alba.getAlbaPrice() + "");
        });
    }

//    @Test
//    public void showTop8ByOOrderByAlbaApplyEndDateDescTest() {
//        albaListService.showTop8ByOOrderByAlbaApplyEndDateDesc().forEach(alba -> {
////            log.info(alba.getAlbaId() + "");
////            log.info(alba.getAlbaTitle() + "");
////            log.info(alba.getAlbaApplyCount() + "");
////            log.info(alba.getAlbaApplyTotalCount() + "");
//            log.info(alba.getAlbaPrice() + "");
//        });
//    }
}
