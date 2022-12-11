package com.codefarm.codefarmer.controller.admin;

import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.service.admin.AdminService;
import com.codefarm.codefarmer.service.admin.InformationService;
import com.codefarm.codefarmer.service.admin.InquireService;
import com.codefarm.codefarmer.service.notice.NoticeFileService;
import com.codefarm.codefarmer.service.notice.NoticeService;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {
    private final AdminService adminService;
//    공지
    private final NoticeService noticeService;
    private final NoticeFileService noticeFileService;
//    작물, 정책
    private final InformationService informationService;
//    문의
    private final InquireService inquireService;

    // 문의 관리
    @GetMapping("/help")
    public String ask(Criteria criteria, Model model, @RequestParam(required = false, defaultValue = "")String keyword, @RequestParam(required = false, defaultValue = "")String searchText, @PageableDefault(size = 10, sort = "inquireId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Inquire> inquires = inquireService.inquireShowAll(pageable, keyword, searchText, searchText, searchText);

        model.addAttribute("inquireCounts", inquireService.countInquire()); // 정책 글 개수
        model.addAttribute("criteria", adminService.createCriteriaPage(pageable, keyword, searchText));
        model.addAttribute("maxPage", 10); // 페이징
        model.addAttribute("inquires", inquires);

        if(keyword.equals("w")) {
            model.addAttribute("resultCount", inquireService.countByNickname(searchText));
        } else {
            model.addAttribute("resultCount", inquires.getTotalPages());
        }
        model.addAttribute("page", pageable);
        model.addAttribute("data", inquires.isEmpty());
        return "/admin/ask";
    }

//    문의 답변 등록
    @GetMapping("/help/answer")
    public String askAnswer(Long inquireId, Model model) {
        InquireAnswer inquireAnswer = inquireService.answerCheck(inquireService.showInquireOne(inquireId));
        boolean answerCheck = inquireAnswer == null;

        if (answerCheck) {
            model.addAttribute("inquireAnswer", new InquireAnswerDTO());
        } else {
            model.addAttribute("inquireAnswerUpdate", inquireAnswer);
        }
        model.addAttribute("answerCheck", answerCheck);
        model.addAttribute("inquire", inquireService.showInquireOne(inquireId));
        return "/admin/ask-detail";
    }

    @PostMapping("/help/answer")
    public RedirectView askAnswerWrite(Long inquireId, InquireAnswerDTO inquireAnswerDTO) {
        inquireAnswerDTO.setInquire(inquireService.showInquireOne(inquireId));
        inquireService.answerAdd(inquireAnswerDTO);
        inquireService.statusUpdate(inquireId, Status.CONFIRM);
        return new RedirectView("/admin/help");
    }
//    문의 답변 수정
    @PostMapping("/help/answer/update")
    public RedirectView askAnswerUpdate(InquireAnswerDTO inquireAnswerDTO) {
        inquireService.answerUpdate(inquireAnswerDTO);
        return new RedirectView("/admin/help");
    }

    // 배너 관리
    @GetMapping("/banner")
    public String adminBanner() {
        return "/admin/banner";
    }

    @GetMapping("/banner-write")
    public String adminBannerWrite() {
        return "/admin/banner-write";
    }

    @GetMapping("/banner-update")
    public String adminBannerUpdate() {
        return "/admin/banner-update";
    }

    // 자유게시판 관리
    @GetMapping("/board")
    public String adminBoard() {return "/admin/board";
    }


    // 농업정보 - 이미지(단일)
    @GetMapping("/crop")
    public String crop(Model model, @RequestParam(required = false, defaultValue = "")String keyword, @RequestParam(required = false, defaultValue = "")String searchText, @PageableDefault(size = 10, sort = "cropId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Crop> crops = informationService.cropSearchShowAll(pageable, keyword, searchText, searchText);

        model.addAttribute("crops", crops);
        model.addAttribute("maxPage", 10); // 페이징
        model.addAttribute("cropCounts", informationService.countByCrop()); // 정책 글 개수
        model.addAttribute("data", crops.isEmpty());
        return "/admin/cropInformation";
    }

//    농업정보 등록
    @GetMapping("/crop/write")
    public String cropWrite(Model model) {
        model.addAttribute("crop", new Crop());
        return "/admin/cropInformation-write";
    }

    @PostMapping("/crop/write") // 저장
    public RedirectView cropWrite(Crop crop, @RequestParam MultipartFile image) throws IOException {
        String path = "C:/upload/crop";
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
            crop.setCropImage(uploadFileName);
        }

        informationService.cropAdd(crop);

        return new RedirectView("/admin/crop");
    }

    //    농업정보 수정
    @GetMapping("/crop/update")
    public String cropUpdate(Long cropId, Model model) {
        model.addAttribute("crop", informationService.cropShowOne(cropId));
        return "/admin/cropInformation-update";
    }

    @PostMapping("/crop/update")
    public RedirectView cropUpdate(Crop crop, @RequestParam MultipartFile image) throws IOException {
        String path = "C:/upload/crop";
        String uploadFileName = null;
        String dbFile = informationService.cropShowOne(crop.getCropId()).getCropImage();

        if (!image.isEmpty()){
            UUID uuid = UUID.randomUUID();
            String fileName = image.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;

            if (uploadFileName != dbFile && dbFile != null){
                File file = new File(path, dbFile);
                if(file.exists()){
                    file.delete();
                }
            }

            File saveFile = new File(path, uploadFileName);
            image.transferTo(saveFile);
            crop.setCropImage(uploadFileName);

        } else if (dbFile != null) {
            File file = new File(path, dbFile);
            if(file.exists()){
                file.delete();
            }
        }
        informationService.cropUpdate(crop);
        return new RedirectView("/admin/crop");
    }

    @GetMapping("/cropimg/display") // 보기
    @ResponseBody
    public byte[] display(String fileName) throws IOException{
        File file = new File("C:/upload/crop", fileName);

        return FileCopyUtils.copyToByteArray(file);
    }

    //    농업정보 삭제
    @PostMapping("/crop/delete")
    public RedirectView cropDelete(Long cropId){
        String path = "C:/upload/crop";
        String dbFile = informationService.cropShowOne(cropId).getCropImage();

        if (dbFile != null) {
            File file = new File(path, dbFile);
            if (file.exists()) {
                file.delete();
            }
        }
        informationService.cropDelete(cropId);
        return new RedirectView("/admin/crop");
    }

    @PostMapping("/one/delete")
    public void delete(String uploadPath, String fileName, boolean fileImageCheck){
    }


    // 알바 관리
    @GetMapping("/job")
    public String adminJob() {return "/admin/job";}

    @GetMapping("/job-participant")
    public String adminJobParticipant() {return "/admin/job-participant";}

    // 메인 관리
    @GetMapping("/main")
    public String adminMain() {return "/admin/main";
    }

    // 멘토 관리
    @GetMapping("/mentor")
    public String adminMentorMentor() {return "/admin/mentor";}

    @GetMapping("/promotion")
    public String adminMentorPromotion(){return "/admin/promotion";}

    // 공지 목록
    @GetMapping("/notice")
    public String adminNotice(Model model, @PageableDefault(size = 10, sort = "NoticeId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Notice> noticeLists = noticeService.showAll(pageable);
        List<Integer> countFiles = new ArrayList<>();
        for (int i=0; i < noticeService.countByNotice(); i++) {
//            noticeFileService.count(noticeLists.get);
//            countFiles.add();
//            countFiles.add(noticeFileService.count(noticeService.showAll(pageable).get(i).getNoticeId()));
        }

        model.addAttribute("countFiles", countFiles);
        model.addAttribute("noticeLists", noticeLists);
        model.addAttribute("maxPage", 10);
        model.addAttribute("noticeCount", noticeService.countByNotice());
        model.addAttribute("data", noticeLists.isEmpty());
        return "/admin/notice";
    }

//    공지 작성
    @GetMapping("/notice/write")
    public String adminNoticeWrite(Model model) {
        model.addAttribute("notice", new NoticeDTO());
        return "/admin/notice-write";
    }

    @PostMapping("/notice/write")
    public RedirectView adminNoticeDetail(NoticeDTO noticeDTO) {
        log.info("첨부 : " + noticeDTO.getNoticeFiles());
        noticeService.register(noticeDTO);
        return new RedirectView("/admin/notice");
    }

//    공지 수정
    @GetMapping("/notice-update")
    public String adminNoticeDetail() {
        return "/admin/notice-update";
    }

//    공지 삭제
    @PostMapping("/notice/delete")
    public RedirectView noticeDelete(Criteria criteria, Long noticeId){
        if(noticeFileService.count(noticeId) > 0) {
            noticeFileService.remove(noticeId);
        }
        noticeService.remove(noticeId);
        return new RedirectView("/admin/notice");
    }

    // 청년정책 관리
    @GetMapping("/policy")
    public String policy(Model model, @RequestParam(required = false, defaultValue = "")String keyword, @RequestParam(required = false, defaultValue = "")String searchText, @PageableDefault(size = 10, sort = "policyId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Policy> policies = informationService.policySearchShowAll(pageable, keyword, searchText, searchText);

        model.addAttribute("policies", policies);
        model.addAttribute("maxPage", 10); // 페이징
        model.addAttribute("policyCounts", informationService.countByPolicy()); // 정책 글 개수
        model.addAttribute("data", policies.isEmpty());
        return "/admin/policy";
    }

//    정책 작성
    @GetMapping("/policy/write")
    public String policyWrite(Model model) {
        model.addAttribute("policy", new Policy());
        return "/admin/policy-write";
    }

    @PostMapping("/policy/write")
    public RedirectView policyWrite(Policy policy) {
        informationService.policyAdd(policy);
//        redirectAttributes.addFlashAttribute("policyId", policy.getPolicyId());
        return new RedirectView("/admin/policy");
    }

//    정책 수정
    @GetMapping("/policy/update")
    public String policyUpdate(Long policyId, Model model) {
        model.addAttribute("policy", informationService.policyShowOne(policyId));
        return "/admin/policy-update";
    }

    @PostMapping("/policy/update")
    public RedirectView policyUpdate(Policy policy) {
        informationService.policyUpdate(policy);
//        redirectAttributes.addFlashAttribute("policyId", policy.getPolicyId());

        return new RedirectView("/admin/policy");
    }

//    정책 삭제
    @PostMapping("/policy/delete")
    public RedirectView policyDelete(Long policyId){
        informationService.policyDelete(policyId);
        return new RedirectView("/admin/policy");
    }

    // 프로그램 관리
    @GetMapping("/program-participant")
    public String adminProgramParticipant() {return "/admin/program-participant";}

    @GetMapping("/program-list")
    public String adminProgramList(){ return "/admin/program-list";}

    @GetMapping("/program-pay")
    public String adminPay(){return "/admin/program-pay";}

    // 댓글 관리
    @GetMapping("/mentor-review")
    public String adminMentorReply() {return "/admin/mentor-review";
    }

    @GetMapping("/board-reply")
    public String adminBoardReply() {return "/admin/board-reply";
    }

    // 사용자 관리
    @GetMapping("/user")
    public String adminUser() {
        return "/admin/user";
    }
}
