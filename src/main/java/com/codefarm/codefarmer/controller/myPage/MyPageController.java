package com.codefarm.codefarmer.controller.myPage;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.service.admin.InquireService;
import com.codefarm.codefarmer.service.alba.AlbaDetailService;
import com.codefarm.codefarmer.service.board.BoardService;
import com.codefarm.codefarmer.service.member.MemberService;
import com.codefarm.codefarmer.service.program.ProgramDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class MyPageController {

    private final MemberService memberService;
    private final InquireService inquireService;
    private final BoardService boardService;
    private final AlbaDetailService albaDetailService;

    //개인정보설정
    @GetMapping("/setting")
//    nav : 마이페이지 메인 화면에서 탭 이동 때 사용
    public Model mainPage(@RequestParam(value = "nav", required = false)String nav, Model model, HttpSession session){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        int countOfInquire = memberService.findMyInquire(member.getMemberId()).size();
//        int countOfBoard = memberService.findMyBoard(member.getMemberId()).size();
        int countOfMyProgram = memberService.findMyProgramRegister(member.getMemberId()).size();
        Long countOfBoard = boardService.showBoardCountMine((Long)session.getAttribute("memberId"));
        model.addAttribute("countOfInquire", countOfInquire);
        model.addAttribute("countOfBoard", countOfBoard);
        model.addAttribute("countOfMyProgram", countOfMyProgram);
        model.addAttribute("mentorAdd", new MentorDTO());
        Mentor mentor = memberService.getMentorInfo((Long)session.getAttribute("memberId"));
        model.addAttribute("mentor", mentor);
        return model.addAttribute(Optional.ofNullable("/myPage/setting").orElse("/myPage/setting" + ("?nav=" + nav)), nav);
    }

    //닉네임변경
    @PostMapping("/settingNick")
    public RedirectView updateNick(HttpSession session, String memberNickname){
        memberService.changeNick((Long)session.getAttribute("memberId"), memberNickname);
        return new RedirectView("/mypage/setting");
    }

    //개인정보변경
    @PostMapping("/settingInfo")
    public RedirectView updateInfo(HttpSession session, String memberPhone, String memberLocation){
        memberService.changeInfo((Long)session.getAttribute("memberId"), memberPhone, memberLocation);
        return new RedirectView("/mypage/setting");
    }

    //멘토신청(멘토타입으로 변경)
    @PostMapping("/settingSave")
    public RedirectView saveMentor(HttpSession session, MentorDTO mentorDTO){
        mentorDTO.setMemberId((Long)session.getAttribute("memberId"));
        memberService.saveMentor(mentorDTO);
        String type = memberService.changeMentorType((Long)session.getAttribute("memberId"));
        session.setAttribute("memberType", type);
        return new RedirectView("/mypage/setting");
    }

    //멘토정보 변경
    @PostMapping("/settingUpdate")
    public RedirectView updateMentor(HttpSession session, String mentorCrop, String mentorYear){
        memberService.changeMentorInfo((Long)session.getAttribute("memberId"), mentorCrop, mentorYear);
        return new RedirectView("/mypage/setting");
    }


    //닉네임 ajax
    @ResponseBody
    @PostMapping("/setting")
    public Integer checkUserNick(@RequestParam("memberNickname") String nickname){
        if(memberService.checkUserNick(nickname) == 1){
            return 1;
        }else {
            return 0;
        }
    }


    //탈퇴
    @GetMapping("/setting/quit")
    public String quitPage(Model model, HttpSession session){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);

        return "/myPage/quit";
    }

    //결제 내역
    @GetMapping("/payment")
    public void paymentPage(Model model, HttpSession session){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }

    //커뮤니티 이동
    @GetMapping("/community")
    public void community(HttpSession session, Model model){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }

    //커뮤니티 글 삭제
    @PostMapping("/community")
    public String communityPage(HttpSession session, Model model, @RequestParam String boardIdString){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        Long boardId = Long.parseLong(boardIdString);
        boardService.removeBoard(boardId);

        return "/myPage/community";
    }

    //프로그램 이동
    @GetMapping("/program")
    public String programPage(HttpSession session, Model model){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        return "/myPage/program";
    }

    //프로그램 신청자 목록 페이지 이동
    @GetMapping("/program/apply")
    public String programApply(Model model, HttpSession session, HttpServletRequest request){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        Long programId = Long.parseLong(request.getParameter("programId"));
        model.addAttribute("member", member);
        log.info("신청자목록 컨트롤러 들어옴");
        List<MemberProgramDTO> memberPrograms = memberService.findMyProgramApplyers((Long)session.getAttribute("memberId"), programId);
        List<MemberProgramDTO> setMemberPrograms = memberPrograms.stream().distinct().collect(Collectors.toList());
        model.addAttribute("memberPrograms", setMemberPrograms);
        model.addAttribute("memberProgram", memberPrograms.isEmpty());
        return "/myPage/programApply";
    }


    //프로그램 신청 취소
    @GetMapping("/program/cancel")
    public String programCancelPage(Model model, HttpSession session, @RequestParam Long programApplyId){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
        MemberProgramDTO applyInfo = memberService.findMyApplyInfo(programApplyId, (Long)session.getAttribute("memberId"));
        model.addAttribute("applyInfo", applyInfo);
        return "/myPage/applyCancel";
    }

    //프로그램 신청 취소 완료
    @PostMapping("/program/cancelOk")
    public RedirectView programCancelOk(@RequestParam String memberProgramIdString){
        log.info("신청취소완료 컨트롤러 들어옴");
        log.info("memberProgramIdString"+memberProgramIdString);
        Long memberProgramId = Long.parseLong(memberProgramIdString);
        memberService.changeMemberStatus(memberProgramId);
        return new RedirectView("/mypage/program");
    }

    //아르바이트 페이지 이동
    @GetMapping("/alba")
    public void albaPage(HttpSession session,Model model){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }


    //아르바이트 신청자 목록 이동
    @GetMapping("/alba/apply")
    public String albaApplyPage(Model model, HttpSession session, HttpServletRequest request){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        Long albaId = Long.parseLong(request.getParameter("albaId"));
        model.addAttribute("member", member);
        List<MemberAlbaDTO> memberAlbas = memberService.findMyAlbaApplyers((Long)session.getAttribute("memberId"), albaId);
        List<MemberAlbaDTO> setmemberAlbas = memberAlbas.stream().distinct().collect(Collectors.toList());
        model.addAttribute("memberAlbas", setmemberAlbas);
        model.addAttribute("memberAlba", memberAlbas.isEmpty());

        return "/myPage/albaApply";
    }

    //아르바이트 삭제
    @PostMapping("/alba/delete")
    public RedirectView delete(@RequestParam String albaIdString){
        log.info("delete컨트롤러 들어옴" );
        log.info("albaId:" + albaIdString);
        Long albaId = Long.parseLong(albaIdString);
        albaDetailService.removeAlbaId(albaId);
        return new RedirectView("/mypage/alba");
    }

    //문의하기 페이지 이동
    @GetMapping("/inquire")
    public void inquirePage(Model model, HttpSession session){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);

    }

    //문의글 답변 페이지 이동
    @GetMapping("/inquire/question")
    public String myInquirePage(Model model, HttpSession session, @RequestParam String inquireId){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        Long inquiredIdL = Long.parseLong(inquireId);
        Inquire inquire = inquireService.showInquireOne(inquiredIdL);
        model.addAttribute("member", member);
        model.addAttribute("inquire", inquire);
        Optional<InquireAnswer> ia = inquireService.showAnswer(inquiredIdL);
        if(ia.isPresent()){
            log.info("ia :"+ia.get());
            log.info("ia :"+ia.get().getInquireAnswer());
            model.addAttribute("inquireAnswer", ia.get());
        }
        log.info("inquire :"+inquire);
        log.info("inquireStatus :"+inquire.getInquireStatus());
        return "/myPage/myInquire";
    }

    //멘토링페이지
    @GetMapping("/mentoring")
    public void mentoringPage(Model model, HttpSession session){
        Member member= memberService.find((Long)session.getAttribute("memberId"));
        model.addAttribute("member", member);
    }


}
