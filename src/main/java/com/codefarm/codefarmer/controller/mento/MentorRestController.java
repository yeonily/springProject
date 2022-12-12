package com.codefarm.codefarmer.controller.mento;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.repository.mentor.MentorCustomRepository;
import com.codefarm.codefarmer.service.mentor.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mentor/*")
public class MentorRestController {
    private final MentorCustomRepository mentorCustomRepository;
    private final MentorService mentorService;

    @GetMapping("list")
    public Slice<MentorBoardDTO> getList(@PageableDefault(size = 8, sort = "UpdatedDate", direction = Sort.Direction.DESC) Pageable pageable){
    return mentorCustomRepository.findAllSliceDTO(pageable);
    }

}
