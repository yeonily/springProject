package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.service.program.ProgramListService;
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
public class ProgramListServiceTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private ProgramListService programListService;

    /*전체 목록 출력*/
    @Test
    public void showAllTest(){
        programListService.showAll().forEach(t -> log.info("목록들은: " + t.toString()));
    }

    /*진행중 프로그램 정렬*/
    @Test
    public void showListByContinueTest() throws Exception{
        programListService.showListByContinue().forEach(t -> log.info("진행중인 프로그램 정렬 : " + t.toString()));
    }

    /*최근 등록일 순 정렬*/
    @Test
    public void showListByRecentApplyDateTest(){
        programListService.showListByRecentApplyDate().forEach(t -> log.info("최근 등록일 순 정렬 :" + t.getProgramApplyStartDate()));
    }

    /*최근 마감일 순 정렬*/
    @Test
    public void showListByRecentEndDateTest(){
        programListService.showListByRecentEndDate().forEach(t -> log.info("최근 마감일 순 정렬: " + t.getProgramApplyEndDate()));
    }

    /*멘티 전용만 보이게 정렬*/
    @Test
    public void showListByOnlyMenteeTest(){
        programListService.showListByOnlyMentee().forEach(t -> log.info("멘티전용만 보이게 정렬: " + t.getProgramType()));
    }

    /*일반인 프로그램만 보이게 정렬*/
    @Test
    public void showListByOnlyUserTest(){
        programListService.showListByOnlyUser().forEach(t -> log.info("일반인 프로그램만 보이게 정렬: " + t.getProgramType()));
    }

    /*유료프로그램만 보이게 정렬*/
    @Test
    public void showListByUsePriceTest(){
        programListService.showListByUsePrice().forEach(t -> log.info("유료프로그램만 보이게 정렬: " + t.getProgramPrice()));
    }

    /*무료프로그램만 보이게 정렬*/
    @Test
    public void showListByFreePriceTest(){
        programListService.showListByFreePrice().forEach(t -> log.info("무료프로그램만 보이게 정렬: " + t.getProgramPrice()));
    }
}
