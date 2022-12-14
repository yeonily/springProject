package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.service.program.ProgramPayService;
import com.codefarm.codefarmer.type.ProgramStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/applypay/*")
@RequiredArgsConstructor
@Slf4j
public class ProgramPayController {

    private final ProgramPayService programPayService;

    @PostMapping(value = "/applyfin" , consumes = "application/json", produces = "text/plain; charset=utf-8")
    public void applyfin(@RequestBody MemberProgramDTO memberProgramDTO , @RequestParam("programId") String programId , HttpSession session)throws Exception{
        Long memberId = (Long)session.getAttribute("memberId");
        Long getProgramId = Long.valueOf(programId);
        log.info("결제 applyfin 들어옴");
        log.info("memberDTO: " + memberProgramDTO.toString());
        log.info("프로그램아이디:" + programId);
        memberProgramDTO.setProgramStatus(ProgramStatus.PAY_SUCCEED);
        programPayService.applyProgram(memberProgramDTO,getProgramId,memberId);




    }
}
