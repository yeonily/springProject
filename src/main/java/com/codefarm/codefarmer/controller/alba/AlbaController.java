package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.service.alba.AlbaDetailService;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alba/*")
@Slf4j
public class AlbaController {

    private final AlbaListService albaListService;
    private final AlbaDetailService albaDetailService;
    private final AlbaRepository albaRepository;

    @GetMapping("/list")
    public String albaList(Model model, @PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) {

        log.info("들어옴1");

        model.addAttribute("lists", albaListService.showListByRecentEndDate());
        model.addAttribute("counts", albaListService.showCount());
        model.addAttribute("recents", albaListService.showByRecent(pageable));

        Page<Alba> albas = albaRepository.findAll(pageable);

        model.addAttribute("albas", albas);
        model.addAttribute("maxPage", 5);
        log.info("진짜?");

        return "/alba/list";

    }

    @GetMapping("/write")
    public void albaWrite(Model model) {
            model.addAttribute("alba", new AlbaDTO());
        }
        
    @PostMapping("/write")
    public RedirectView albaWrite(AlbaDTO albaDTO, String albaApplyStartDateString, String albaApplyEndDateString, String albaWorkDateString, @RequestParam MultipartFile file) throws Exception {

        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/image/alba");
//        String path = new ClassPathResource("/static/image/information/crop").getFile().getAbsolutePath();

        if (!file.isEmpty()) {
//           String fullPath = "C://upload/" + file.getOriginalFilename();
            String fullPath = path + "/" + file.getOriginalFilename();
            log.info("파일 저장 -> " + fullPath);
            file.transferTo(new File(fullPath));
            albaDTO.setAlbaImage(fullPath);
        }

        log.info("들어왔니?");
        log.info("albaApplyStartDateString:"+ albaApplyStartDateString);
        log.info("albaApplyEndDateString:"+ albaApplyEndDateString);
        log.info("albaWorkDateString:"+ albaWorkDateString);
        log.info("albaDTO: " + albaDTO.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY, 0)
                .toFormatter();

        LocalDateTime albaWorkDateTest = LocalDate.parse(albaWorkDateString, formatter).atStartOfDay();
        log.info("1" + albaWorkDateTest);

        LocalDateTime albaApplyStartDateTest = LocalDate.parse(albaApplyStartDateString, formatter).atStartOfDay();
        log.info("2" + albaApplyStartDateTest);

        LocalDateTime albaApplyEndDateTest = LocalDate.parse(albaApplyEndDateString, formatter).atStartOfDay();
        log.info("3" + albaApplyEndDateTest);

        albaDTO.setAlbaApplyStartDate(albaApplyStartDateTest);
        albaDTO.setAlbaApplyEndDate(albaApplyEndDateTest);
        albaDTO.setAlbaWorkDate(albaWorkDateTest);

        albaListService.saveAll(albaDTO);

//        redirectAttributes.addFlashAttribute("albaId", albaDTO.getAlbaId());
        return new RedirectView("list");
    }

    @GetMapping("/img")
    public String imgPage() {
        return "/alba/img";
    }

    @PostMapping("/img")
    public String imgPage(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        log.info("name -> " + name);
        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/image/alba");
//        String path = new ClassPathResource("/static/image/information/crop").getFile().getAbsolutePath();

        if (!file.isEmpty()) {
//           String fullPath = "C://upload/" + file.getOriginalFilename();
            String fullPath = path + "/" + file.getOriginalFilename();
            log.info("파일 저장 -> " + fullPath);
            file.transferTo(new File(fullPath));

        }

        return "/alba/img";
    }

    @GetMapping("/detail")
    public void albaDetail(Model model, @RequestParam Long albaId) {
        log.info("디테일 들어옴");
        log.info("alba : "+ albaId);

        AlbaDTO list = albaDetailService.showByAlbaId(albaId);
        model.addAttribute("list",list);
    }

//    @GetMapping("/display")
//    public byte[] display(String fileName) throws IOException{
//        File file = new File("")
//    }


}
