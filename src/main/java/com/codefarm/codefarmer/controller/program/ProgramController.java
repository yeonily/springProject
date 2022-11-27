package com.codefarm.codefarmer.controller.program;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/program/*")
public class ProgramController {

    @GetMapping("/list")
    public void list(){

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
