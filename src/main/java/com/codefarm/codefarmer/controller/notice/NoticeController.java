package com.codefarm.codefarmer.controller.notice;

import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String noticePage(Model model, Criteria criteria, @PageableDefault(size = 10, sort = "NoticeId", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Notice> noticeLists = noticeService.showAll(pageable);
        if(criteria.getPage() == 0) {
            criteria.createCriteria(pageable.getPageNumber());
        }

        model.addAttribute("noticeLists", noticeLists);
        model.addAttribute("maxPage", 5);
        return "/notice/notice";
    }

    @GetMapping("/detail")
    public void detailPage(Criteria criteria, Long noticeId, Model model){
        noticeService.updateViewCount(noticeId);
        model.addAttribute("notice", noticeService.showOne(noticeId));
    }
}
