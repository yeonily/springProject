package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alba/*")
@Slf4j
public class AlbaController {

    private final AlbaListService albaListService;



    @GetMapping("/list")
    public void albaList(Model model) {
        log.info("들어옴1");
        model.addAttribute("lists", albaListService.showTop8ByOOrderByAlbaApplyEndDateDesc());
        model.addAttribute("counts",albaListService.showAlbaTotalCount());
        model.addAttribute("recents",albaListService.showListByRecent());
    }

    @GetMapping("/write")
    public void albaWrite() {

    }

    @GetMapping("/detail")
    public void albaDetail() {

    }
}
