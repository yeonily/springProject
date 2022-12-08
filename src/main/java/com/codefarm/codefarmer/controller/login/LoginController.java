package com.codefarm.codefarmer.controller.login;

import com.codefarm.codefarmer.service.join.JoinKakaoService;
import com.codefarm.codefarmer.service.join.JoinService;
import com.codefarm.codefarmer.service.login.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/login/*")
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;

    @ResponseBody
    @GetMapping("/kakao")
    public RedirectView kakaoLogin(String code, HttpSession session) throws Exception {
        log.info("코드 : "+code);
        String token = kakaoService.getKakaoAccessToken(code);
        String email = kakaoService.getKakaoEmailByToken(token);
        Long oauthId = kakaoService.getKakaoIdByToken(token);
        String memberOauthId = oauthId+"k";
        Long id = kakaoService.selectId(memberOauthId);
        String type = kakaoService.selectType(memberOauthId);

        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);
        session.setAttribute("token", token);

        return new RedirectView("/main/main");
    }

    @GetMapping("/logoutkakao")
    public RedirectView kakaoLogout(HttpSession session){
        log.info("logout");
        kakaoService.quitKakao((String)session.getAttribute("token"));
        session.invalidate();

        return new RedirectView("/main/main");
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
