package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.service.alba.AlbaDetailService;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.UUID;

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

        log.info("테스트 : " + albaListService.showListByRecentEndDate().size());
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
    public RedirectView albaWrite(AlbaDTO albaDTO, String albaApplyStartDateString, String albaApplyEndDateString, String albaWorkDateString, @RequestParam MultipartFile image) throws Exception {

        String path = "/Users/yeontaegwan/Desktop/project/image";
        String uploadFileName = null;

        File uploadPath = new File(path);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        if (!image.isEmpty()){
            UUID uuid = UUID.randomUUID();
            String fileName = image.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            File saveFile = new File(path, uploadFileName);
            image.transferTo(saveFile);
            albaDTO.setAlbaImage(uploadFileName);
        }

        log.info("들어왔니?");

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

        return new RedirectView("/alba/list");
    }

    @GetMapping("/detail")
    public void albaDetail(Model model, @RequestParam Long albaId) throws Exception {
        log.info("디테일 들어옴");
        log.info("alba : "+ albaId);

        AlbaDTO list = albaDetailService.showByAlbaId(albaId);
        model.addAttribute("list",list);
    }

    @GetMapping("/delete")
    public RedirectView albaDelete(Long albaId){
        log.info("albaId : " + albaId);
        log.info("게시글 삭제 되니?");
        albaDetailService.removeAlbaId(albaId);
        return new RedirectView("/alba/list");
    }

    //    알바 게시글 수정
    @GetMapping("/update")
    public String albaUpdate(Long albaId, Model model) {
        model.addAttribute("alba", albaDetailService.albaShowOne(albaId));
        return "/alba/update";
    }

    @PostMapping("/update")
    public RedirectView albaUpdate(AlbaDTO albaDTO, @RequestParam MultipartFile image) throws IOException {
        String path = "/Users/yeontaegwan/Desktop/project/image";
        String uploadFileName = null;
        String dbFile = albaDetailService.albaShowOne(albaDTO.getAlbaId()).getAlbaImage();

        if (!image.isEmpty()){
            UUID uuid = UUID.randomUUID();
            String fileName = image.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;

            if (uploadFileName != dbFile && dbFile != null){
                File file = new File(path, dbFile);
                log.info("파일 있니? -> " + file);
                if(file.exists()){
                    file.delete();
                }
            }

            File saveFile = new File(path, uploadFileName);
            image.transferTo(saveFile);
            albaDTO.setAlbaImage(uploadFileName);

        } else if (dbFile != null) {
            File file = new File(path, dbFile);
            if(file.exists()){
                file.delete();
            }
        }
        albaDetailService.albaUpdate(albaDTO);
        return new RedirectView("/alba/list");
    }

    @GetMapping("/albaimg/display") // 보기
    @ResponseBody
    public byte[] display(String fileName) throws IOException{
        File file = new File("/Users/yeontaegwan/Desktop/project/image", fileName);

        return FileCopyUtils.copyToByteArray(file);
    }



}
