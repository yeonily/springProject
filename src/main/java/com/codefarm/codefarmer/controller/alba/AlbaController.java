package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public void albaWrite(Model model) {
            model.addAttribute("alba", new AlbaDTO());
        }
        
    @PostMapping("/write")
    public RedirectView albaWrite(AlbaDTO albaDTO, RedirectAttributes redirectAttributes){
        log.info("들어왔니?");

        log.info(albaDTO.toString());
        albaListService.add(albaDTO);
        redirectAttributes.addFlashAttribute("albaId", albaDTO.getAlbaId());
        return new RedirectView("/list");
    }

    @GetMapping("/detail")
    public void albaDetail() {

    }
}
