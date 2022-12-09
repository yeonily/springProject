package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.service.program.ProgramListService;
import com.codefarm.codefarmer.service.program.ProgramRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sort/*")
public class ProgramListSortController {

    @Autowired
    private ProgramListService programListService;

    /*전체보기 버튼*/
    @PostMapping("/showall")
    public List<ProgramDTO> showAllList() throws Exception{
        //프로그램목록을 불러와서 프로그램dto 1개를 거기에 있는 files전체를 불러와서 넣어주는거(set)
        List<ProgramDTO> programs = programListService.showAll();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        log.info("전체 : "  + programs.toString());
//        programs.stream().map(t -> t.setFiles(programListService.showFiles(t.getProgramId()));
        return programs;
    }

    /*진행중 버튼*/
    @PostMapping("/continue")
    public List<ProgramDTO> showListByContinue() throws Exception{
        List<ProgramDTO> programs = programListService.showListByContinue();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        return programs;
//        return programListService.showListByContinue();
    }

    /*최근 등록일 순*/
    @PostMapping("/recentApply")
    public List<ProgramDTO> showListByRecentApplyDate() throws Exception{
        List<ProgramDTO> programs = programListService.showListByRecentApplyDate();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        return programs;
    }

    /*최근 마감일 순*/
    @PostMapping("/recentEnd")
    public List<ProgramDTO> showListByRecentEndDate() throws Exception{
        List<ProgramDTO> programs = programListService.showListByRecentEndDate();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        return programs;
    }
    /*멘티 전용*/
    @PostMapping("/onlyMentee")
    public List<ProgramDTO> showListByOnlyMentee() throws Exception{
        List<ProgramDTO> programs = programListService.showListByOnlyMentee();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        return programs;
    }

    /*일반인용*/
    @PostMapping("/onlyUser")
    public List<ProgramDTO> showListByOnlyUser() throws Exception{
        List<ProgramDTO> programs = programListService.showListByOnlyUser();
        log.info("일반인용 수: " + programs.size());
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        log.info("일반인용 수2: " + programs.size());
        return programs;
    }
    /*유료*/
    @PostMapping("/usePrice")
    public List<ProgramDTO> showListByUsePrice() throws Exception{
        List<ProgramDTO> programs = programListService.showListByUsePrice();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        programs.stream().forEach(t -> log.info("유료금액:" + t.getProgramPrice()));
        return programs;
    }

    /*무료*/
    @PostMapping("/freePrice")
    public List<ProgramDTO> showListByFreePrice() throws Exception{
        List<ProgramDTO> programs = programListService.showListByFreePrice();
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        programs.stream().forEach(t -> log.info("무료금액:" + t.toString()));
        return programs;
    }


}
