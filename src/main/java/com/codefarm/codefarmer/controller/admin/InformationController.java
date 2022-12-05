package com.codefarm.codefarmer.controller.admin;


import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.service.admin.InformationService;
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
@RequestMapping("/information/*")
public class InformationController {
    private final InformationService informationService;

    @GetMapping("/policy")
    public void policy(Model model, @PageableDefault(size = 10, sort = "PolicyId", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Policy> policies = informationService.policyShowAll(pageable);

        model.addAttribute("maxPage", 5);
        model.addAttribute("policies", policies);
    }

    @GetMapping("/crops")
    public void cropInformation(Model model, @PageableDefault(size = 10, sort = "CropId", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Crop> crops = informationService.cropShowAll(pageable);

        model.addAttribute("maxPage", 5);
        model.addAttribute("crops", crops);
    }

    @GetMapping("/step") public void stepTest(){;}

    @GetMapping("/ready") public void readyTest(){;}
}
