package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.service.program.ProgramListService;
import com.codefarm.codefarmer.type.Oauth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sort/*")
public class ProgramListSortController {

    @Autowired
    private ProgramListService programListService;

    /*진행중 버튼*/
    @PostMapping("/continue")
    public List<ProgramDTO> showListByContinue() throws Exception{
        log.info("들어옴");
//        programListService.showListByContinue().stream().map(t -> t.getMemberId()).forEach(t-> log.info("======" + t));
//        log.info("============================" + programListService.showListByContinue());
        programListService.showListByContinue().forEach(t -> log.info(t.toString()));
        return programListService.showListByContinue();
//        return programListService.showListByContinue();
    }

    /*최근 등록일 순*/
    @PostMapping("/recentApply")
    public List<ProgramDTO> showListByRecentApplyDate() throws Exception{
        return programListService.showListByRecentApplyDate();
    }

    /*최근 마감일 순*/
    @PostMapping("/recentEnd")
    public List<ProgramDTO> showListByRecentEndDate() throws Exception{
        return programListService.showListByRecentEndDate();
    }
    /*멘티 전용*/
    @PostMapping("/onlyMentee")
    public List<ProgramDTO> showListByOnlyMentee() throws Exception{
        return programListService.showListByOnlyMentee();
    }

    /*일반인용*/
    @PostMapping("/onlyUser")
    public List<ProgramDTO> showListByOnlyUser() throws Exception{
        return programListService.showListByOnlyUser();
    }
    /*유료*/
    @PostMapping("/usePrice")
    public List<ProgramDTO> showListByUsePrice() throws Exception{
        return programListService.showListByUsePrice();
    }

    /*무료*/
    @PostMapping("/freePrice")
    public List<ProgramDTO> showListByFreePrice() throws Exception{
        return programListService.showListByFreePrice();
    }


}
