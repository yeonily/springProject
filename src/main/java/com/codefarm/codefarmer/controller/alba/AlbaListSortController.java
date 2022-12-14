package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kind/*")
public class AlbaListSortController {

    @Autowired
    private AlbaListService albaListService;

    @Autowired
    private AlbaRepository albaRepository;

//    메인 화면
    @PostMapping("/main")
    public List<AlbaDTO> showListByRecentEndDate(){
        albaListService.showListByRecentEndDate().forEach(a -> log.info(a.toString()));
        return albaListService.showListByRecentEndDate();
    }

//    게시글 총 개수
    @PostMapping("/count")
    public Long showCount(){
        return albaListService.showCount();
    }

//    시급순 버튼
    @PostMapping("/payList")
    public List<AlbaDTO> showListByHighPay(Model model, @PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        albaListService.showListByHighPay(pageable).forEach(a -> log.info(a.toString()));

        List<AlbaDTO> albas = albaListService.showListByHighPay(pageable);

        model.addAttribute("albas", albas);
        model.addAttribute("maxPage", 5);

        return albaListService.showListByHighPay(pageable);
    }

//    @GetMapping("/payList/{page}/{keyword}")
//    public AlbaDTO getAlbaList(@PathVariable int page, @PathVariable(required = false) String keyword){
//        String decodeKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
//
//        Criteria criteria = new Criteria();
//        criteria.setPage(page);
//        criteria.setKeyword(decodeKeyword);
//
//        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 12);
//
//        Page<AlbaDTO> albaDTOPage = albaListService.findByAlbaPriceOrderByAlbaPriceDesc(pageable, criteria);
//        int endPage = (int)(Math.ceil(albaDTOPage.getNumber()+1 / (double)10)) * 10;
//        if(albaDTOPage.getTotalPages() < endPage){
//            endPage = albaDTOPage.getTotalPages() == 0 ? 1 : albaDTOPage.getTotalPages();
//        }
//
//        AlbaDTO albaDTO = new AlbaDTO();
//
//        albaDTO.setAlbaList(albaDTOPage.getContent());
//        albaDTO.setEndPage(endPage);
//
//        albaDTOPage.getContent().stream().map(AlbaDTO::toString).forEach(log::info);
//
//        return albaDTO;
//    }

//    최신순 버튼
    @PostMapping("/newList")
    public List<AlbaDTO> showByRecent(Model model, @PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        albaListService.showByRecent(pageable).forEach(a->log.info(a.toString()));

        List<AlbaDTO> albas = albaListService.showByRecent(pageable);

        model.addAttribute("albas", albas);
        model.addAttribute("maxPage", 5);

        return albaListService.showByRecent(pageable);
    }

//    마감순 버튼
    @PostMapping("/collectList")
    public List<AlbaDTO> showListByEndDate(Model model,@PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        albaListService.showListByEndDate(pageable).forEach(a->log.info(a.toString()));

        List<AlbaDTO> albas = albaListService.showListByEndDate(pageable);

        model.addAttribute("albas", albas);
        model.addAttribute("maxPage", 5);

        return albaListService.showListByEndDate(pageable);
    }

//    페이징 처리
    @PostMapping("/page")
    public void pagingpage(Model model, @PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{

        log.info("페이징 들어옴");

    }

}
