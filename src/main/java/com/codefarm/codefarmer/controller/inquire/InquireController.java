package com.codefarm.codefarmer.controller.inquire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/inquire/*")
public class InquireController {

    @GetMapping("/writeInquire")
    public void inquire(){

    }
}
