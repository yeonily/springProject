package com.codefarm.codefarmer.controller.myPage;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.service.admin.InquireService;
import com.codefarm.codefarmer.service.board.ReplyService;
import com.codefarm.codefarmer.service.member.MemberService;
import com.codefarm.codefarmer.service.mentor.MentorMenteeService;
import com.codefarm.codefarmer.service.program.ProgramListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/mypage/*")
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class MyPageRestController {

    private final MemberService memberService;
    private final ProgramListService programListService;
    private final MentorMenteeService mentorMenteeService;


    //프로그램 목록(farmer)
    @GetMapping("/programlist")
    public List<ProgramDTO> showAllPgList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<ProgramDTO> programs = memberService.findMyProgramRegister(memberId);
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        log.info("전체 : "  + programs.toString());

        return programs;
    }

    //알바 목록(farmer)
    @GetMapping("/albalist")
    public List<AlbaDTO> showAllAbList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<AlbaDTO> albas = memberService.findMyAlbaRegister(memberId);
        return albas;
    }

    //멘토저장
    @PostMapping("/savementor")
    public String saveMentor(@RequestBody MentorDTO mentorDTO, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.saveMentor(mentorDTO);
        return "insert success";
    }

    //게시글 목록
    @GetMapping("/boardlist")
    public List<BoardDTO> showAllBoList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<BoardDTO> boards = memberService.findMyBoard(memberId);
        List<BoardDTO> setboards = boards.stream().distinct().collect(Collectors.toList());
        return setboards;
    }



    //문의글 목록
    @GetMapping("/inquirelist")
    public List<InquireDTO> showAllIqList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<InquireDTO> inquires = memberService.findMyInquire(memberId);
        return inquires;
    }

    //알바상태 변경 - 수락
    @PostMapping("/alba/confirm")
    public String albaConfirm(@RequestParam String albaApplyId, @RequestParam String albaId){
        log.info("신청취소완료 컨트롤러 들어옴");
        log.info("memberProgramIdString"+albaApplyId);
        Long albaApplyIdL = Long.parseLong(albaApplyId);
        Long albaIdL = Long.parseLong(albaId);
        memberService.changeMemberAlbaConfirm(albaApplyIdL);
        memberService.plusAlbaCount(albaIdL);

        return "success";
    }

    //알바상태 변경 - 거절
    @PostMapping("/alba/reject")
    public String albaReject(@RequestParam String albaApplyId){
        log.info("신청취소완료 컨트롤러 들어옴");
        log.info("memberProgramIdString"+albaApplyId);
        Long albaApplyIdL = Long.parseLong(albaApplyId);
        memberService.changeMemberAlbaReject(albaApplyIdL);

        return "success";
    }

    /*----------------------------*/
    //프로그램 목록(user)
    @GetMapping("/program/applylist")
    public List<ProgramDTO> showApplyPgList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<ProgramDTO> programs = memberService.findMyProgramApply(memberId);
        for (ProgramDTO program : programs){
            program.setFiles(programListService.showFiles(program.getProgramId()));
        }
        log.info("전체 : "  + programs.toString());

        return programs;
    }

    //알바 목록(user)
    @GetMapping("/alba/applylist")
    public List<MemberAlbaDTO> showApplyAbList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<MemberAlbaDTO> albas = memberService.findMyAlbaApply(memberId);
        return albas;
    }

    //결제내역 목록(user)
    @GetMapping("/paylist")
    public List<MemberProgramDTO> showAllPayList(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("아이디 : "+ memberId);
        List<MemberProgramDTO> memberPrograms = memberService.findMyPay(memberId);
        return memberPrograms;
    }

    //---------------------------------
    //멘토 목록
    @GetMapping("/mentorlist")
    public List<MentorMenteeDTO> showMentorList(HttpSession session){
        Long menteeId = (Long)session.getAttribute("memberId");
        List<MentorMenteeDTO> mentors = mentorMenteeService.findByMenteeId(menteeId);
        return mentors;
    }

    //멘토 삭제
    @PostMapping("/mentor/delete")
    public String mentorDelete(@RequestParam String mentorMenteeId){
        log.info("멘토 삭제 컨트롤러 들어옴");
        log.info("멘토 삭제 컨트롤러 들어옴" + mentorMenteeId);
        Long mentorMenteeIdL = Long.parseLong(mentorMenteeId);
        mentorMenteeService.removeById(mentorMenteeIdL);

        return "delete success";
    }

    //-----------------------------------
    //멘티 목록
    @GetMapping("/menteelist")
    public List<MentorMenteeDTO> showMenteeList(HttpSession session){
        Long mentorId = (Long)session.getAttribute("memberId");
        List<MentorMenteeDTO> mentees = mentorMenteeService.findByMentorId(mentorId);
        return mentees;
    }
}
