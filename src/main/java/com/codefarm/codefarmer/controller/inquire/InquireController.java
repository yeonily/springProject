package com.codefarm.codefarmer.controller.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.service.admin.InquireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/help")
@Slf4j
public class InquireController {
    private final InquireService inquireService;
    private final FarmerRepository farmerRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String inquireWrite(Model model){
        model.addAttribute("inquireDTO", new InquireDTO());
        return "/inquire/writeInquire";
    }

    @PostMapping
    public RedirectView inquireWrite(InquireDTO inquireDTO, RedirectAttributes redirectAttributes){
//        log.info("누구냐 ㅡㅡ -> " + userRepository.findById(11L).get());
//        inquireDTO.setMember(farmerRepository.findById(10L).get());

        inquireService.inquireAdd(inquireDTO);
        redirectAttributes.addFlashAttribute("inquireId", inquireDTO.getInquireId());
        return new RedirectView("/help");
    }

}
