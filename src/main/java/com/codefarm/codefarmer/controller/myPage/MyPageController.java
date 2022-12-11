package com.codefarm.codefarmer.controller.myPage;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.service.admin.InquireService;
import com.codefarm.codefarmer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class MyPageController {

    private final MemberService memberService;
    private final InquireService inquireService;

    @GetMapping("/setting")
//    nav : 마이페이지 메인 화면에서 탭 이동 때 사용
    public Model mainPage(@RequestParam(value = "nav", required = false)String nav, Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        int countOfInquire = memberService.registerMyInquire(member.getMemberId()).size();
        int countOfBoard = memberService.registerMyBoard(member.getMemberId()).size();
        int countOfMyProgram = memberService.registerMyProgram(member.getMemberId()).size();
        model.addAttribute("countOfInquire", countOfInquire);
        model.addAttribute("countOfBoard", countOfBoard);
        model.addAttribute("countOfMyProgram", countOfMyProgram);
        return model.addAttribute(Optional.ofNullable("/myPage/setting").orElse("/myPage/setting" + ("?nav=" + nav)), nav);
    }


    @ResponseBody
    @PostMapping("/setting")
    public Integer checkUserNick(@RequestParam("memberNickname") String nickname){
        if(memberService.checkUserNick(nickname) == 1){
            return 1;
        }else {
            return 0;
        }
    }



    @GetMapping("/setting/quit")
    public String quitPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);

        return "/myPage/quit";
    }

    @GetMapping("/payment")
    public void paymentPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }

    @GetMapping("/community")
    public void communityPage(HttpSession session, Model model){
        List<Board> boards = memberService.registerMyBoard((Long)session.getAttribute("memberId"));
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("boards", boards);
        model.addAttribute("member", member);
    }

//    @GetMapping("/program")
//    public void programPage(HttpSession session, Model model){
//        List<Program> programs = memberService.registerMyProgram((Long)session.getAttribute("memberId"));
//        Member member= memberService.select((Long)session.getAttribute("memberId"));
//        model.addAttribute("programs", programs);
//        model.addAttribute("member", member);
//    }

    @GetMapping("/program/apply")
    public String programApplyPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        return "/myPage/programApply";
    }

    @GetMapping("/program/cancel")
    public String programCancelPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        return "/myPage/applyCancel";
    }

//    @GetMapping("/alba")
//    public void albaPage(HttpSession session,Model model){
//        List<AlbaDTO> albas = memberService.registerMyAlba((Long)session.getAttribute("memberId"));
//        Member member= memberService.select((Long)session.getAttribute("memberId"));
//        model.addAttribute("albas", albas);
//        model.addAttribute("member", member);
//    }

    @GetMapping("/alba/apply")
    public String albaApplyPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        return "/myPage/albaApply";
    }

    @GetMapping("/inquire")
    public void inquirePage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        List<Inquire> inquires = memberService.registerMyInquire((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        model.addAttribute("inquires", inquires);

    }

    @GetMapping("/inquire/question")
    public String myInquirePage(Model model, HttpSession session, @RequestParam Long inquireId){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        Inquire inquire = inquireService.showInquireOne(inquireId);
        model.addAttribute("member", member);
        model.addAttribute("inquire", inquire);
        log.info("id"+inquireId);
        return "/myPage/myInquire";
    }

    @GetMapping("/mentoring")
    public void mentoringPage(Model model, HttpSession session){
        Member member= memberService.select((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }
}
