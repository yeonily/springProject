package com.codefarm.codefarmer.controller.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.admin.InquireService;
import com.codefarm.codefarmer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/help")
@Slf4j
public class InquireController {
    private final InquireService inquireService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping
    public String inquireWrite(Model model){
        model.addAttribute("inquireDTO", new InquireDTO());
        return "/inquire/writeInquire";
    }

    @PostMapping
    public RedirectView inquireWrite(InquireDTO inquireDTO, HttpSession session, RedirectAttributes redirectAttributes){
        Member member= memberService.find((Long)session.getAttribute("memberId"));

        inquireDTO.setMember(member);
        inquireService.inquireAdd(inquireDTO);
        redirectAttributes.addFlashAttribute("inquireId", inquireDTO.getInquireId());
        return new RedirectView("/help");
    }

}
