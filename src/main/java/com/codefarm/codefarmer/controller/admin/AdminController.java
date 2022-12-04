package com.codefarm.codefarmer.controller.admin;

import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.service.admin.InformationService;
import com.codefarm.codefarmer.service.notice.NoticeFileService;
import com.codefarm.codefarmer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {
//    공지
    private final NoticeService noticeService;
    private final NoticeFileService noticeFileService;
//    작물, 정책
    private final InformationService informationService;

    // 문의 관리
    @GetMapping("/ask")
    public String adminAsk() {return "/admin/ask";}

    @GetMapping("/ask-detail")
    public String adminAskDetail() {return "/admin/ask-detail";}

    // 배너 관리
    @GetMapping("/banner")
    public String adminBanner() {
        return "/admin/banner";
    }

    @GetMapping("/banner-write")
    public String adminBannerWrite() {
        return "/admin/banner-write";
    }

    @GetMapping("/banner-update")
    public String adminBannerUpdate() {
        return "/admin/banner-update";
    }

    // 자유게시판 관리
    @GetMapping("/board")
    public String adminBoard() {return "/admin/board";
    }

    // 농업정보 관리
    @GetMapping("/crop")
    public String adminCropInformation() {return "/admin/cropInformation";
    }

    @GetMapping("/cropInformation-write")
    public String adminCropInformationWrite() {return "/admin/cropInformation-write";
    }

    @GetMapping("/cropInformation-update")
    public String adminCropInformationUpdate() {return "/admin/cropInformation-update";
    }

//    농업정보 삭제
    @PostMapping("/crop/delete")
    public RedirectView cropDelete(Long cropId){
        informationService.cropDelete(cropId);
        return new RedirectView("/admin/crop");
    }

    // 알바 관리
    @GetMapping("/job")
    public String adminJob() {return "/admin/job";}

    @GetMapping("/job-participant")
    public String adminJobParticipant() {return "/admin/job-participant";}

    // 메인 관리
    @GetMapping("/main")
    public String adminMain() {return "/admin/main";
    }

    // 멘토 관리
    @GetMapping("/mentor")
    public String adminMentorMentor() {return "/admin/mentor";}

    @GetMapping("/promotion")
    public String adminMentorPromotion(){return "/admin/promotion";}

    // 공지 목록
    @GetMapping("/notice")
    public String adminNotice(Model model) {
        List<Integer> countFiles = new ArrayList<>();
        for (int i=0; i < noticeService.countByNotice(); i++) {
            countFiles.add(noticeFileService.count(noticeService.showAll().get(i).getNoticeId()));
        }

        model.addAttribute("countFiles", countFiles);
        model.addAttribute("noticeLists", noticeService.showAll());
        model.addAttribute("noticeCount", noticeService.countByNotice());
        return "/admin/notice";
    }

//    공지 작성
    @GetMapping("/notice-write")
    public String adminNoticeWrite(Model model) {
        model.addAttribute("notice", new Notice());
        return "/admin/notice-write";
    }

    @PostMapping("/notice-write")
    public RedirectView adminNoticeDetail(Notice notice, RedirectAttributes redirectAttributes) {
        noticeService.register(notice);
        redirectAttributes.addFlashAttribute("noticeId", notice.getNoticeId());
        return new RedirectView("/admin/notice-write");
    }

//    공지 수정
    @GetMapping("/notice-update")
    public String adminNoticeDetail() {
        return "/admin/notice-update";
    }

//    공지 삭제
    @PostMapping("/notice/delete")
    public RedirectView noticeDelete(Long noticeId){
        if(noticeFileService.count(noticeId) > 0) {
            noticeFileService.remove(noticeId);
        }
        noticeService.remove(noticeId);
        return new RedirectView("/admin/notice");
    }

    // 청년정책 관리
    @GetMapping("/policy")
    public String adminPolicy(Model model) {
        model.addAttribute("policyLists", informationService.policySelectAll());
        model.addAttribute("policyCounts", informationService.countByPolicy());
        return "/admin/policy";
    }

//    정책 작성
    @GetMapping("/policy/write")
    public String adminPolicyWrite(Model model) {
        model.addAttribute("policy", new Policy());
        return "/admin/policy-write";
    }

    @PostMapping("/policy/write")
    public RedirectView policyWrite(Policy policy, RedirectAttributes redirectAttributes) {
        informationService.policyAdd(policy);
        redirectAttributes.addFlashAttribute("policyId", policy.getPolicyId());
        return new RedirectView("/admin/policy");
    }

//    정책 수정
    @GetMapping("/policy-update")
    public String adminPolicyUpdate() {return "/admin/policy-update";
    }

//    정책 삭제
    @PostMapping("/policy/delete")
    public RedirectView policyDelete(Long policyId){
        log.info("정책번호 -> " + policyId);
        informationService.policyDelete(policyId);
        return new RedirectView("/admin/policy");
    }

    // 프로그램 관리
    @GetMapping("/program-participant")
    public String adminProgramParticipant() {return "/admin/program-participant";}

    @GetMapping("/program-list")
    public String adminProgramList(){ return "/admin/program-list";}

    @GetMapping("/program-pay")
    public String adminPay(){return "/admin/program-pay";}

    // 댓글 관리
    @GetMapping("/mentor-review")
    public String adminMentorReply() {return "/admin/mentor-review";
    }

    @GetMapping("/board-reply")
    public String adminBoardReply() {return "/admin/board-reply";
    }

    // 사용자 관리
    @GetMapping("/user")
    public String adminUser() {
        return "/admin/user";
    }
}
