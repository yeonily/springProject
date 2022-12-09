package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.service.program.ProgramDetailService;
import com.codefarm.codefarmer.service.program.ProgramListService;
import com.codefarm.codefarmer.service.program.ProgramRegisterService;
import com.codefarm.codefarmer.service.program.ProgramUpdateService;
import com.codefarm.codefarmer.type.ProgramLevel;
import com.codefarm.codefarmer.type.ProgramType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.zip.DataFormatException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/program/*")
public class ProgramController {

    private final ProgramListService programListService;
    private final ProgramDetailService programDetailService;
    private final ProgramRegisterService programRegisterService;
    private final ProgramFileRepository programFileRepository;
    private final ProgramUpdateService programUpdateService;

    @GetMapping("/list")
    public void list(Model model){
        List<ProgramDTO> lists = programListService.showAll();
        model.addAttribute("lists",lists);
    }

    @GetMapping("/detail")
    public void detail(Model model,@RequestParam Long programId){
        log.info("상세페이지 들어옴");
        log.info("programId:" + programId);
        ProgramDTO list = programDetailService.showByProgramId(programId);
        list.setFiles(programDetailService.showFiles(programId));
        log.info("리스트 내용: " );
//        List<ProgramDTO> lists = programListService.();
        model.addAttribute("list",list);
    }

    @GetMapping("/apply")
    public void apply(){

    }

    @GetMapping("/applyfin")
    public void applyfin(){

    }

    @GetMapping("/pay")
    public void pay(){

    }

    @GetMapping("/register")
    public void register(Model model){
        model.addAttribute("programRegister" , new ProgramDTO());
    }

    @PostMapping("/register")
    public RedirectView registerFin(ProgramDTO programDTO, ProgramFileDTO programFileDTO, HttpSession session ,String programWorkDateString, String programWorkStartTimeString, String programWorkEndTimeString, String programApplyStartDateString, String programApplyEndDateString , String programTypeString, String programLevelString) throws DateTimeParseException {
        log.info("리스폰스바디 컨트롤러 들어옴");
        log.info("programTypeString: " + programTypeString);
        log.info("programLevelString: " + programLevelString);
        Long sessionId = (Long)session.getAttribute("memberId");

//       세션에 memberId 넣기
        programDTO.setMemberId(sessionId);

//        글 등록 시 일반인용,멘티전용 따라 DTO에 값 넣기
        if(programTypeString.equals("일반인용")){
            programDTO.setProgramType(ProgramType.ALL_USER);
        }else{
            programDTO.setProgramType(ProgramType.ONLY_MENTEE);
        }

//        글 등록 시 초급,중급,고급 따라 DTO에 값 넣기
        if(programLevelString.equals("초급")){
            programDTO.setProgramLevel(ProgramLevel.BASIC);
        }else if(programLevelString.equals("중급")){
            programDTO.setProgramLevel(ProgramLevel.SKILL_UP);
        }else{
            programDTO.setProgramLevel(ProgramLevel.MASTER);
        }

//        log.info("files: " + programDTO.getFiles());
//        programDTO.getFiles().stream().map(t -> programFileDTO.setFileName(t.getFileName()));
//        log.info("file[0].fileName:"+ programDTO.getFiles().get(0).getFileName());
//        log.info("file[0].fileName:"+ programDTO.getFiles().get(0).getFileUploadPath());
//        log.info("file[0].fileName:"+ programDTO.getFiles().get(0).getFileUuid());
//        log.info("file[0].fileName:"+ programDTO.getFiles().get(0).getFileSize());

//      태관 참고
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY, 0)
                .toFormatter();

//        태관 참고
        LocalDateTime programWorkDateTest = LocalDate.parse(programWorkDateString, formatter).atStartOfDay();

        log.info("1" + programWorkDateTest);
        LocalDateTime programWorkStartTimeTest = LocalDateTime.parse(programWorkStartTimeString, formatter1);
        log.info("2" + programWorkStartTimeTest);
        LocalDateTime programWorkEndTimeTest = LocalDateTime.parse(programWorkEndTimeString, formatter1);
        log.info("3" + programWorkEndTimeTest);
        LocalDateTime programApplyStartDateTest = LocalDate.parse(programApplyStartDateString, formatter).atStartOfDay();
        LocalDateTime programApplyEndDateTest = LocalDate.parse(programApplyEndDateString, formatter).atStartOfDay();

//        태관 참고
        programDTO.setProgramWorkDate(programWorkDateTest);
        programDTO.setProgramWorkStartTime(programWorkStartTimeTest);
        programDTO.setProgramWorkEndTime(programWorkEndTimeTest);
        programDTO.setProgramApplyStartDate(programApplyStartDateTest);
        programDTO.setProgramApplyEndDate(programApplyEndDateTest);

        /*Program program = null;
        program.changeFiles(programDTO.getFiles());*/

//        태관 참고
        programRegisterService.saveAll(programDTO);

//        programDTO.getFiles().stream().map(t -> programFileRepository.saveAll(t));


//        programFileRepository.save(programDTO.getFiles())

//        redirectAttributes.addFlashAttribute("boardNumber", boardVO.getBoardNumber());
        return new RedirectView("list");
    }
    @GetMapping("/update")
    public void update(Model model , @RequestParam Long programId){
        ProgramDTO updateRegister = programUpdateService.showUpdate(programId);
        model.addAttribute("updateRegister" , updateRegister);
    }

    @PostMapping("/update")
    public RedirectView updateFin( ){

        return new RedirectView("list");
    }

}
