package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.alba.AlbaDetailService;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import com.codefarm.codefarmer.service.member.MemberService;
import com.codefarm.codefarmer.type.MemberType;
import com.codefarm.codefarmer.type.Status;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alba/*")
@Slf4j
public class AlbaController {

    private final AlbaListService albaListService;
    private final AlbaDetailService albaDetailService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    // 알바메인페이지 리스트
    @GetMapping("/list")
    public void albaList(Model model, MemberDTO memberDTO, HttpSession session) {

        log.info("들어옴1");
        Long memberId = (Long)session.getAttribute("memberId");
//        model.addAttribute("lists", albaListService.showListByRecentEndDate());
//        model.addAttribute("counts", albaListService.showCount());
        model.addAttribute("memberId", memberId);

        if(memberId!=null) {
            model.addAttribute("memberType", albaListService.getMemberType(memberId));
            log.info("memberType" + albaListService.getMemberType(memberId));
        }

        log.info("진짜?");
    }

    // 알바 글 등록
    @GetMapping("/write")
    public void albaWrite(Model model, HttpSession session) {


        Long memberId = (Long)session.getAttribute("memberId");
        model.addAttribute("alba", new AlbaDTO());

        if(memberId!=null) {
            Optional<Member> member = memberRepository.findById(memberId);
            String name = member.get().getMemberName();
            model.addAttribute("name", name);
        }
    }

    // 알바 글 등록
    @PostMapping("/write")
    public RedirectView albaWrite(Model model, AlbaDTO albaDTO, HttpSession session, String albaApplyStartDateString, String albaApplyEndDateString, String albaWorkDateString, @RequestParam MultipartFile image) throws Exception {

        // 알바 글쓰기 세션 아이디 저장
        Long memberId = (Long)session.getAttribute("memberId");

        if(memberId!=null) {
//            String path = "/Users/yeontaegwan/Desktop/project/image";
          String path = "C:/upload";
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
            albaDTO.setMemberId(memberId);

            albaListService.saveAll(albaDTO);
        }


        return new RedirectView("/alba/list");
    }

    // 알바 상세정보 불러오기
    @GetMapping("/detail")
    public void albaDetail(Model model,HttpSession session, @RequestParam Long albaId, @RequestParam(required = false) String albaIdString) throws Exception {
        log.info("디테일 들어옴");
        log.info("alba : "+ albaId);

        Long memberId = (Long)session.getAttribute("memberId");

        if(memberId!=null) {
            Long writerId = albaDetailService.memberIdFind(albaId);
            Long cancel = albaDetailService.albaSelect(albaId, memberId);
            model.addAttribute("cancel",cancel);
            model.addAttribute("memberType", albaListService.getMemberType(memberId));
            model.addAttribute("memberId",memberId);
            model.addAttribute("writerId",writerId);
        }
        model.addAttribute("albaId",albaId);
        AlbaDTO list = albaDetailService.showByAlbaId(albaId);
        model.addAttribute("list",list);
    }

    // 알바 게시글 삭제
    @GetMapping("/delete")
    public RedirectView albaDelete(Long albaId){
        log.info("albaId(delete) : " + albaId);
        albaDetailService.removeAlbaId(albaId);
        return new RedirectView("/alba/list");
    }

    // 알바 게시글 수정
    @GetMapping("/update")
    public String albaUpdate(HttpSession session, Long albaId, Model model) throws IOException {
        Long memberId = (Long)session.getAttribute("memberId");

        AlbaDTO updateAlba = albaDetailService.showByAlbaId(albaId);
        log.info("enter?");
        log.info("albaId : " + albaId);
        model.addAttribute("updateAlba", updateAlba);

        if(memberId!=null) {
            Optional<Member> member = memberRepository.findById(memberId);
            String name = member.get().getMemberName();
            model.addAttribute("name", name);
        }

        return "/alba/update";
    }

    // 알바 게시글 수정
    @PostMapping("/update")
    public RedirectView albaUpdate(AlbaDTO albaDTO,HttpSession session, String albaApplyStartDateString, String albaApplyEndDateString, String albaWorkDateString, @RequestParam MultipartFile image, RedirectAttributes redirectAttributes) throws Exception {

        Long memberId = (Long)session.getAttribute("memberId");

        log.info("몇 번째일까요? : " + albaDTO.getAlbaId());
//        String path = "/Users/yeontaegwan/Desktop/project/image";
        String path = "C:/upload";
        String uploadFileName = null;
        String dbFile = albaDetailService.showByAlbaId(albaDTO.getAlbaId()).getAlbaImage();

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY, 0)
                .toFormatter();

        LocalDateTime albaWorkDateTest = LocalDate.parse(albaWorkDateString, formatter).atStartOfDay();
        log.info("1." + albaWorkDateTest);

        LocalDateTime albaApplyStartDateTest = LocalDate.parse(albaApplyStartDateString, formatter).atStartOfDay();
        log.info("2." + albaApplyStartDateTest);

        LocalDateTime albaApplyEndDateTest = LocalDate.parse(albaApplyEndDateString, formatter).atStartOfDay();
        log.info("3." + albaApplyEndDateTest);

        albaDTO.setAlbaApplyStartDate(albaApplyStartDateTest);
        albaDTO.setAlbaApplyEndDate(albaApplyEndDateTest);
        albaDTO.setAlbaWorkDate(albaWorkDateTest);
        albaDTO.setMemberId(memberId);

        log.info("entering???");
        albaDetailService.albaUpdate(albaDTO);
        return new RedirectView("/alba/list");
    }

    // 이미지 보기
    @GetMapping("/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException{
//        File file = new File("/Users/yeontaegwan/Desktop/project/image", fileName);
        File file = new File("C:/upload", fileName);

        return FileCopyUtils.copyToByteArray(file);
    }

    // 알바 지원하기
//    @PostMapping("/apply")
//    public RedirectView albaApply(HttpSession session , MemberAlbaDTO memberAlbaDTO) throws Exception{
//        Long memberId = (Long)session.getAttribute("memberId");
//
//        log.info("어플라이 알바아이디 : " + memberAlbaDTO.getAlbaId().toString());
//        log.info("memberId : " + memberId);
//
//        memberAlbaDTO.setMemberStatus(Status.WAITING);
//        memberAlbaDTO.setMemberId(memberId);
//        memberAlbaDTO.setAlbaId(memberAlbaDTO.getAlbaId());
//
//        albaDetailService.albaApply(memberAlbaDTO);
//
//        return new RedirectView("/alba/list");
//    }

//    // 지원 취소하기
//    @PostMapping("/applyCancel")
//    public RedirectView albaApplyCancel(HttpSession session, Long albaId) throws Exception{
//        Long memberId = (Long)session.getAttribute("memberId");
//        log.info("sessionMemberId : " + memberId);
//        log.info("albaID : " + albaId);
//
//        log.info("select : " + albaDetailService.albaSelect(albaId, memberId));
////        log.info("albaApplyId : " + albaApplyId);
//
////        model.addAttribute("albaApplyId", albaDetailService.albaApplyCancel(albaApplyId));
//        Long applyCancelId = albaDetailService.albaSelect(albaId, memberId);
//
//        albaDetailService.albaApplyCancel(applyCancelId);
//
//        return new RedirectView("/alba/list");
//    }



}
