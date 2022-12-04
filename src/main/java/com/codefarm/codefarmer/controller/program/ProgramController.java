package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.service.program.ProgramDetailService;
import com.codefarm.codefarmer.service.program.ProgramListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/program/*")
public class ProgramController {

    private final ProgramListService programListService;

    private final ProgramDetailService programDetailService;

    @GetMapping("/list")
    public void list(Model model){
        log.info("들어옴1");
        List<ProgramDTO> lists = programListService.showAll();
        model.addAttribute("lists",lists);
    }

    @GetMapping("/detail")
    public void detail(Model model,@RequestParam Long programId){
        log.info("상세페이지 들어옴");
        log.info("programId:" + programId);
        ProgramDTO list = programDetailService.showByProgramId(programId);
        log.info("리스트 내용: " );
//        List<ProgramDTO> lists = programListService.();
        model.addAttribute("list",list);
    }

    @GetMapping("/apply")
    public void apply(){

    }

    @GetMapping("/applyfin")
    public void applyfin(){

    }

    @GetMapping("/pay")
    public void pay(){

    }

    @GetMapping("/register")
    public void register(){

    }

}
