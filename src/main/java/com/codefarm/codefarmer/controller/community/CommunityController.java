package com.codefarm.codefarmer.controller.community;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value={"/community", "/community/*"})
public class CommunityController {

    @GetMapping("")
    public String communityPage(){
        return "/community/community";
    }

    @GetMapping("/detail")
    public void detailPage(){
    }

    @GetMapping("/register")
    public void writePage(){
    }
}
