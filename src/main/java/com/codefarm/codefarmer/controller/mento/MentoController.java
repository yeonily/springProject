package com.codefarm.codefarmer.controller.mento;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mento/*")
public class MentoController {

    @GetMapping("/intro")
    public void mentoIntro(){

    }

    @GetMapping("/list")
    public void list(){

    }
    @GetMapping("/detail")
    public void detail(){

    }
    @GetMapping("/write")
    public void write(){

    }
    @GetMapping("/chatting")
    public void chatting(){

    }
}
