package com.codefarm.codefarmer.controller.information;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/information/*")
public class PolicyController {

    @GetMapping("/step") public void stepTest(){;}

    @GetMapping("/ready") public void readyTest(){;}

    @GetMapping("/policy")
    public void policy(){

    }
}
