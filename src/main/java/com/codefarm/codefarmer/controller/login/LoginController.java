package com.codefarm.codefarmer.controller.login;

import com.codefarm.codefarmer.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired private LoginService loginService;

    @GetMapping("/kakao")
    public ModelAndView kakaoCallback(@RequestParam("code") String code, HttpSession httpSession){
        ModelAndView mav = new ModelAndView();
        //1번 인증코드로 요청 전달
        String accessToken = loginService.getAccessToken(code);
        //2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = loginService.getUserInfo(accessToken);

        System.out.println("login info: " + userInfo.toString());

        if(userInfo.get("email") != null){
            httpSession.setAttribute("userId", userInfo.get("email"));
            httpSession.setAttribute("access_token", accessToken);
        }
        mav.addObject("userId", userInfo.get("email"));
        mav.setViewName("main");
        return mav;
    }

    @GetMapping("")
    public String loginPage(){
            return "/login/login";
        }

    @GetMapping("/agreement")
    public String agreementPage(){
        return "/join/agreement";
    }




}
