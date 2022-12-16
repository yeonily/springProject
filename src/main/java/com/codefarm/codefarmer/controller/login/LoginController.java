package com.codefarm.codefarmer.controller.login;

import com.codefarm.codefarmer.service.join.JoinKakaoService;
import com.codefarm.codefarmer.service.join.JoinService;
import com.codefarm.codefarmer.service.login.KakaoService;
import com.codefarm.codefarmer.service.login.NaverService;
import com.codefarm.codefarmer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/login/*")
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final NaverService naverService;

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
        if(kakaoService.checkOauth(memberOauthId) == 0){
            return new RedirectView("/register/form");
        }
        return new RedirectView("/main/main");
    }

    @GetMapping("/logoutkakao")
    public RedirectView kakaoLogout(HttpSession session){
        log.info("logout");
        kakaoService.logoutKakao((String)session.getAttribute("token"));
        session.invalidate();

        return new RedirectView("/main/main");
    }

    @GetMapping("/quitkakao")
    public RedirectView kakaoQuit(HttpSession session){
        log.info("quit");
        memberService.secession((Long)session.getAttribute("memberId"));
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


    @GetMapping("/naver")
    public RedirectView naverLogin(@RequestParam String code, HttpSession session) throws Exception {
        log.info("코드 : "+code);

        String token = naverService.getNaverAccessToken(code);
        String email = naverService.getNaverEmailByToken(token);
        String oauthId = naverService.getNaverIdByToken(token);
        String memberOauthId = oauthId+"n";
        Long id = naverService.selectId(memberOauthId);
        String type = naverService.selectType(memberOauthId);

        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);
        session.setAttribute("token", token);

        if (naverService.checkOauth(memberOauthId) == 0){
            return new RedirectView("/register/form");
        }

        return new RedirectView("/main/main");

    }

    @GetMapping("/test")
    public String loginTest(){
        return "/login/test";
    }
}
