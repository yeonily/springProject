package com.codefarm.codefarmer.controller.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value={"/notice", "/notice/*"})
public class NoticeController {

    @GetMapping("")
    public String noticePage(){
        return "/notice/notice";
    }

    @GetMapping("/detail")
    public void detailPage(){
    }
}
