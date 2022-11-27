package com.codefarm.codefarmer.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

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
    @GetMapping("/cropInformation")
    public String adminCropInformation() {return "/admin/cropInformation";
    }

    @GetMapping("/cropInformation-write")
    public String adminCropInformationWrite() {return "/admin/cropInformation-write";
    }

    @GetMapping("/cropInformation-update")
    public String adminCropInformationUpdate() {return "/admin/cropInformation-update";
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

    // 공지사항 관리
    @GetMapping("/notice")
    public String adminNotice() {return "/admin/notice";
    }

    @GetMapping("/notice-write")
    public String adminNoticeWrite() {return "/admin/notice-write";
    }

    @GetMapping("/notice-update")
    public String adminNoticeDetail() {return "/admin/notice-update";
    }

    // 청년정책 관리
    @GetMapping("/policy")
    public String adminPolicy() {return "/admin/policy";
    }

    @GetMapping("/policy-write")
    public String adminPolicyWrite() {return "/admin/policy-write";
    }

    @GetMapping("/policy-update")
    public String adminPolicyUpdate() {return "/admin/policy-update";
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
