//package com.codefarm.codefarmer.controller.mento;
//
//import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
//import com.codefarm.codefarmer.service.mentor.MentorService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.RedirectView;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping(value = {"/mentorr/*"})
//public class MentorController {
//
//    private final MentorService mentorService;
//
//    @GetMapping("/write")
//    public void write(Model model){
//        model.addAttribute("mentorBoard", new MentorBoardDTO());
//    }
//
//    @PostMapping("/write")
//    public RedirectView writeFin(MentorBoardDTO mentorBoardDTO, RedirectAttributes redirectAttributes, HttpSession session){
//        Long sessionId = (Long)session.getAttribute("memberId");
//
////        mentorBoardDTO.setMemberId(112L);
//        mentorBoardDTO.setMemberId(sessionId);
//        mentorService.mentorBoardAdd(mentorBoardDTO);
//        redirectAttributes.addFlashAttribute("mentorBoardId", mentorBoardDTO.getMentorBoardId());
//
//        return new RedirectView("/mento/list");
//    }
//}
