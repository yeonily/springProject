package com.codefarm.codefarmer.controller.notice;

import com.codefarm.codefarmer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value={"/notice", "/notice/*"})
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("")
    public String noticePage(Model model){
        model.addAttribute("noticeLists", noticeService.showAll());
        return "/notice/notice";
    }

    @GetMapping("/detail")
    public void detailPage(Long noticeId, Model model){
        noticeService.updateViewCount(noticeId);
        model.addAttribute("notice", noticeService.showOne(noticeId));
    }
}
