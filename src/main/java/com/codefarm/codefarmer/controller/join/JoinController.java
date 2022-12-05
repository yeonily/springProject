package com.codefarm.codefarmer.controller.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/register/*")
public class JoinController {

    @GetMapping("")
    public String joinPage(){
        return "/join/join";
    }

    @GetMapping("/form")
    public String joinFormPage(){


        return "/join/joinForm";
    }

    @GetMapping("/agreement")
    public String agreementPage(){
        return "/join/agreement";
    }
}
