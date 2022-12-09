package com.codefarm.codefarmer.controller.join;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.service.join.JoinKakaoService;
import com.codefarm.codefarmer.service.join.JoinService;
import com.codefarm.codefarmer.service.member.MemberService;
import com.codefarm.codefarmer.type.MemberType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/register/*")
@RequiredArgsConstructor
public class JoinController {

    private final JoinKakaoService joinKakaoService;
    private final JoinService joinService;
    private final MemberService memberService;

    @GetMapping("")
    public String joinPage(){
        return "/join/join";
    }

    @ResponseBody
    @GetMapping("/kakao")
    public RedirectView kakaoLogin(String code, RedirectAttributes redirectAttributes,HttpSession session){
        log.info("코드 : "+code);
        String token = joinKakaoService.getKakaoAccessToken(code);
        session.setAttribute("token", token);
        try {
            String memberEmail = joinKakaoService.getKakaoEmailByToken(token);
            Long id = joinKakaoService.getKakaoIdByToken(token);
            String memberOauthId = id+"k";
            log.info("아이디: " + memberOauthId);
            log.info("이메일: " + memberEmail);
            redirectAttributes.addFlashAttribute("memberOauthId", memberOauthId);
            redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
            if(joinService.checkOauth(memberOauthId) == 1){
                return new RedirectView("/login/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/register/form");
    }

    @GetMapping("/form")
    public String joinFormPage(MemberDTO memberDTO){
        return "/join/joinForm";
    }

    @GetMapping("/agreement")
    public String agreementPage(){
        return "/join/agreement";
    }

    @ResponseBody
    @PostMapping("/form")
    public Integer checkUserNick(@RequestParam("memberNickname") String nickname){
        if(joinService.checkUserNick(nickname) == 1){
            return 1;
        }else {
            return 0;
        }
    }


    @PostMapping("/joinOk")
    public String joinOk(MemberDTO memberDTO, HttpSession session){
        memberService.join(memberDTO);
        Long id = joinService.selectId(memberDTO.getMemberOauthId());
        String type = joinService.selectType(memberDTO.getMemberOauthId());
        log.info("멤버11:"+memberDTO);
        log.info("멤버95:"+memberDTO.getMemberType());



        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);
        Long value = (Long)session.getAttribute("memberId");
        log.info("세션 value"+ value);
        log.info("세션 stringv"+ type);
        return "/main/main";
    }


}
