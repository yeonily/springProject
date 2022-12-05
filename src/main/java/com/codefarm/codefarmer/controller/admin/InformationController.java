package com.codefarm.codefarmer.controller.admin;


import com.codefarm.codefarmer.domain.admin.Criteria;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import com.codefarm.codefarmer.service.admin.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/information/*")
public class InformationController {
    private final InformationService informationService;

    @GetMapping("/policy")
    public void policy(Criteria criteria, Model model){
        model.addAttribute("policyLists", informationService.policyShowAll(criteria));
    }

    @GetMapping("/crops")
    public void cropInformation(Model model){
        model.addAttribute("cropLists", informationService.cropShowAll());
    }

    @GetMapping("/step") public void stepTest(){;}

    @GetMapping("/ready") public void readyTest(){;}
}
