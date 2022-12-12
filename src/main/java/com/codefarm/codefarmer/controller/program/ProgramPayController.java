package com.codefarm.codefarmer.controller.program;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applypay/*")
@Slf4j
public class ProgramPayController {

    @PostMapping("/kakaoapply")
    public void chargePoint(Long amount){
        log.info("결제 들어옴!");
        log.info("금액은: "+ amount);
    }
}
