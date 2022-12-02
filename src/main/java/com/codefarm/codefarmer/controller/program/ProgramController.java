package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.service.program.ProgramListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/program/*")
public class ProgramController {

    private final ProgramListService programListService;

    @GetMapping("/list")
    public void list(Model model){
        List<ProgramDTO> lists = programListService.showAll();
        model.addAttribute("lists",lists);
    }

    @GetMapping("/detail")
    public void detail(){

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
