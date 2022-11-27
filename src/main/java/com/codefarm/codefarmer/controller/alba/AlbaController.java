package com.codefarm.codefarmer.controller.alba;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alba/*")
public class AlbaController {

    @GetMapping("/list")
    public void albaList() {

    }

    @GetMapping("/write")
    public void albaWrite() {

    }

    @GetMapping("/detail")
    public void albaDetail() {

    }
}
