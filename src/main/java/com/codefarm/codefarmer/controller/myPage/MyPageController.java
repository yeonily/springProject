package com.codefarm.codefarmer.controller.myPage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
public class MyPageController {

    @GetMapping("/setting")
//    tab : 마이페이지 메인 화면에서 탭 이동 때 사용
    public Model mainPage(@RequestParam(value = "nav", required = false)String nav, Model model){
        return model.addAttribute(Optional.ofNullable("/myPage/setting").orElse("/myPage/setting" + ("?nav=" + nav)), nav);
    }

    @GetMapping("/setting/quit")
    public String quitPage(){
        return "/myPage/quit";
    }

    @GetMapping("/payment")
    public void paymentPage(){
    }

    @GetMapping("/community")
    public void communityPage(){
    }

    @GetMapping("/program")
    public void programPage(){
    }

    @GetMapping("/program/apply")
    public String programApplyPage(){
        return "/myPage/programApply";
    }

    @GetMapping("/program/cancel")
    public String programCancelPage(){
        return "/myPage/applyCancel";
    }

    @GetMapping("/alba")
    public void albaPage(){
    }

    @GetMapping("/alba/apply")
    public String albaApplyPage(){
        return "/myPage/albaApply";
    }

    @GetMapping("/inquire")
    public void inquirePage(){
    }

    @GetMapping("/inquire/qustion")
    public String myInquirePage(){
        return "/myPage/myInquire";
    }

    @GetMapping("/mentoring")
    public void mentoringPage(){
    }
}
