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
import com.codefarm.codefarmer.entity.admin.Criteria;
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
import com.codefarm.codefarmer.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ResponseBody
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
    @GetMapping("/albalist/{page}")
    public Page<AlbaDTO> showAllAbList(HttpSession session, @PathVariable int page) {
        Long memberId = (Long)session.getAttribute("memberId");

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<AlbaDTO> albas = memberService.findMyAlbaRegister(memberId, pageable);
        log.info("albas : " + albas);

        int endPage = (int)(Math.ceil(albas.getNumber()+1 / (double)10)) * 10;
        if(albas.getTotalPages() < endPage){
            endPage = albas.getTotalPages() == 0 ? 1 : albas.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + albas.getTotalPages());
        log.info("albaDTOPage.getSize() : " + albas.getSize());
        log.info("albaDTOPage.getTotalElements() : " + albas.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + albas.hasNext());

        albas.stream().map(albaDTO -> albaDTO.getAlbaTitle()).forEach(log::info);

        return memberService.findMyAlbaRegister(memberId, pageable);
    }

    //멘토저장
    @PostMapping("/savementor")
    public String saveMentor(@RequestBody MentorDTO mentorDTO, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.saveMentor(mentorDTO);
        return "insert success";
    }

    //게시글 목록
    @GetMapping("/boardlist/{page}")
    public Page<BoardDTO> showAllBoList(HttpSession session, @PathVariable int page) {
        Long memberId = (Long)session.getAttribute("memberId");
        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

//        List<BoardDTO> boards = memberService.findMyBoard(memberId);
//        List<BoardDTO> setboards = boards.stream().distinct().collect(Collectors.toList());

        Page<BoardDTO> boards = memberService.findMyBoard(memberId, pageable);
        log.info("boards : " + boards);

        int endPage = (int)(Math.ceil(boards.getNumber()+1 / (double)10)) * 10;
        if(boards.getTotalPages() < endPage){
            endPage = boards.getTotalPages() == 0 ? 1 : boards.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + boards.getTotalPages());
        log.info("albaDTOPage.getSize() : " + boards.getSize());
        log.info("albaDTOPage.getTotalElements() : " + boards.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + boards.hasNext());

        boards.stream().map(boardDTO -> boardDTO.getBoardTitle()).forEach(log::info);

        return memberService.findMyBoard(memberId, pageable);
    }



    //문의글 목록
    @GetMapping("/inquirelist/{page}")
    public Page<InquireDTO> showAllIqList(HttpSession session, @PathVariable int page) {
        Long memberId = (Long)session.getAttribute("memberId");
        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<InquireDTO> inquires = memberService.findMyInquire(memberId, pageable);
        log.info("inquires : " + inquires);

        int endPage = (int)(Math.ceil(inquires.getNumber()+1 / (double)10)) * 10;
        if(inquires.getTotalPages() < endPage){
            endPage = inquires.getTotalPages() == 0 ? 1 : inquires.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + inquires.getTotalPages());
        log.info("albaDTOPage.getSize() : " + inquires.getSize());
        log.info("albaDTOPage.getTotalElements() : " + inquires.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + inquires.hasNext());

        inquires.stream().map(inquireDTO -> inquireDTO.getInquireQTitle()).forEach(log::info);

        return memberService.findMyInquire(memberId, pageable);
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
    @GetMapping("/alba/applylist/{page}")
    public Page<MemberAlbaDTO> showApplyAbList(HttpSession session, @PathVariable int page) {
        Long memberId = (Long)session.getAttribute("memberId");

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<MemberAlbaDTO> albas = memberService.findMyAlbaApply(memberId, pageable);
        log.info("albas : " + albas);

        int endPage = (int)(Math.ceil(albas.getNumber()+1 / (double)10)) * 10;
        if(albas.getTotalPages() < endPage){
            endPage = albas.getTotalPages() == 0 ? 1 : albas.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + albas.getTotalPages());
        log.info("albaDTOPage.getSize() : " + albas.getSize());
        log.info("albaDTOPage.getTotalElements() : " + albas.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + albas.hasNext());

        albas.stream().map(memberAlbaDTO -> memberAlbaDTO.getMemberName()).forEach(log::info);

        return memberService.findMyAlbaApply(memberId, pageable);
    }

    //결제내역 목록(user)
    @GetMapping("/paylist/{page}")
    public Page<MemberProgramDTO> showAllPayList(HttpSession session, @PathVariable int page) {
        Long memberId = (Long)session.getAttribute("memberId");

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<MemberProgramDTO> memberPrograms = memberService.findMyPay(memberId, pageable);
        log.info("memberPrograms : " + memberPrograms);

        int endPage = (int)(Math.ceil(memberPrograms.getNumber()+1 / (double)10)) * 10;
        if(memberPrograms.getTotalPages() < endPage){
            endPage = memberPrograms.getTotalPages() == 0 ? 1 : memberPrograms.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + memberPrograms.getTotalPages());
        log.info("albaDTOPage.getSize() : " + memberPrograms.getSize());
        log.info("albaDTOPage.getTotalElements() : " + memberPrograms.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + memberPrograms.hasNext());

        memberPrograms.stream().map(memberProgramDTO -> memberProgramDTO.getProgramTitle()).forEach(log::info);

        return memberService.findMyPay(memberId, pageable);
    }

    //---------------------------------
    //멘토 목록
    @GetMapping("/mentorlist")
    public MentorMenteeDTO[] showMentorList(HttpSession session){
        Long menteeId = (Long)session.getAttribute("memberId");
        List<MentorMenteeDTO> mentors = mentorMenteeService.findByMenteeId(menteeId);

        int listSize = mentors.size();
        MentorMenteeDTO[] mentorsAr = mentors.toArray(new MentorMenteeDTO[listSize]);
        log.info("mentors "+ mentors);
        return mentorsAr;
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
    //멘티 목록 - confirm
    @PostMapping("/menteelist/confirm")
    public MentorMenteeDTO[] showMenteeList(HttpSession session, @RequestParam(defaultValue = "CONFIRM") Status status){
        log.info("컨펌 목록 컨트롤러 들어옴");
        log.info("status "+status);
//        Status status_s = Status.valueOf(status);
        Long mentorId = (Long)session.getAttribute("memberId");
        List<MentorMenteeDTO> mentees = mentorMenteeService.findByMentorId(mentorId, status);

        int listSize = mentees.size();
        MentorMenteeDTO[] menteesAr = mentees.toArray(new MentorMenteeDTO[listSize]);
        log.info("mentees "+ mentees);
        return menteesAr;

    }

    //멘티 목록 - waiting, reject
    @PostMapping("/menteelist/other")
    public MentorMenteeDTO[] showMenteeApplyList(HttpSession session, @RequestParam(defaultValue = "WAITING") Status status){
        log.info("신청 목록 컨트롤러 들어옴");
        log.info("status "+status);
        Long mentorId = (Long)session.getAttribute("memberId");
        List<MentorMenteeDTO> mentees = mentorMenteeService.findByMentorId(mentorId, status);

        int listSize = mentees.size();
        log.info("listSize "+listSize);
        MentorMenteeDTO[] menteesAr = mentees.toArray(new MentorMenteeDTO[listSize]);
        log.info("mentees "+ mentees);
        return menteesAr;
    }



    //멘티 삭제
    @PostMapping("/mentee/delete")
    public String menteeDelete(@RequestParam String mentorMenteeId){
        log.info("멘티 삭제 컨트롤러 들어옴");
        log.info("멘티 삭제 컨트롤러 들어옴" + mentorMenteeId);
        Long mentorMenteeIdL = Long.parseLong(mentorMenteeId);
        mentorMenteeService.removeById(mentorMenteeIdL);

        return "delete success";
    }

    //멘티상태 변경 - 수락
    @PostMapping("/mentee/confirm")
    public String menteeConfirm(@RequestParam String mentorMenteeId, @RequestParam String menteeId, HttpSession session){
        log.info("수락 컨트롤러 들어옴");
        log.info("memberProgramIdString"+mentorMenteeId);
        Long mentorMenteeIdL = Long.parseLong(mentorMenteeId);
        mentorMenteeService.changeConfirmStatus(mentorMenteeIdL); //멘티상태변경
        //memberService.changeMenteeType((Long)session.getAttribute("memberId")); //멤버타입변경
        Long memberId = Long.parseLong(menteeId);
        String type = memberService.changeMenteeType(memberId);
        session.setAttribute("memberType", type);
        return "success";
    }

    //멘티상태 변경 - 거절
    @PostMapping("/mentee/reject")
    public String menteeReject(@RequestParam String mentorMenteeId){
        log.info("거절 컨트롤러 들어옴");
        log.info("memberProgramIdString"+mentorMenteeId);
        Long mentorMenteeIdL = Long.parseLong(mentorMenteeId);
        mentorMenteeService.changeRejectStatus(mentorMenteeIdL);
        return "success";
    }
}
