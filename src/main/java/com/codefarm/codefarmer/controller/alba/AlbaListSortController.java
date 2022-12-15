package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/alba/*")
public class AlbaListSortController {

    @Autowired
    private AlbaListService albaListService;

    // 메인 화면
    @PostMapping("/list/main")
    public List<AlbaDTO> showListByRecentEndDate(){
        albaListService.showListByRecentEndDate().forEach(a -> log.info(a.toString()));
        return albaListService.showListByRecentEndDate();
    }

    // 게시글 총 개수
    @PostMapping("/list/count")
    public Long showCount(){
        return albaListService.showCount();
    }


    // 최신순
    @GetMapping("/list/newlist/{page}")
    public Page<AlbaDTO> newList(@PathVariable int page){
        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<AlbaDTO> albaDTOPage = albaListService.showAlbaNew(pageable);
        log.info("albaDTOPage : " + albaDTOPage);

        int endPage = (int)(Math.ceil(albaDTOPage.getNumber()+1 / (double)10)) * 10;
        if(albaDTOPage.getTotalPages() < endPage){
            endPage = albaDTOPage.getTotalPages() == 0 ? 1 : albaDTOPage.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + albaDTOPage.getTotalPages());
        log.info("albaDTOPage.getSize() : " + albaDTOPage.getSize());
        log.info("albaDTOPage.getTotalElements() : " + albaDTOPage.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + albaDTOPage.hasNext());

        albaDTOPage.stream().map(AlbaDTO::getAlbaAddress).forEach(log::info);

        return albaListService.showAlbaNew(pageable);
    }

    // 시급순
    @GetMapping("/list/paylist/{page}")
    public Page<AlbaDTO> payList(@PathVariable int page){

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<AlbaDTO> albaDTOPage = albaListService.showAlbaPay(pageable);
        log.info("albaDTOPage : " + albaDTOPage);

        int endPage = (int)(Math.ceil(albaDTOPage.getNumber()+1 / (double)10)) * 10;
        if(albaDTOPage.getTotalPages() < endPage){
            endPage = albaDTOPage.getTotalPages() == 0 ? 1 : albaDTOPage.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + albaDTOPage.getTotalPages());
        log.info("albaDTOPage.getSize() : " + albaDTOPage.getSize());
        log.info("albaDTOPage.getTotalElements() : " + albaDTOPage.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + albaDTOPage.hasNext());

        albaDTOPage.stream().map(AlbaDTO::getAlbaAddress).forEach(log::info);

        return albaListService.showAlbaPay(pageable);
    }

    // 마감순
    @GetMapping("/list/endlist/{page}")
    public Page<AlbaDTO> endList(@PathVariable int page){

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        log.info("page : " + page);
        log.info("criteria : " + criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        log.info("pageable :" + pageable);

        Page<AlbaDTO> albaDTOPage = albaListService.showAlbaEnd(pageable);
        log.info("albaDTOPage : " + albaDTOPage);

        int endPage = (int)(Math.ceil(albaDTOPage.getNumber()+1 / (double)10)) * 10;
        if(albaDTOPage.getTotalPages() < endPage){
            endPage = albaDTOPage.getTotalPages() == 0 ? 1 : albaDTOPage.getTotalPages();
        }

        log.info("endPage : " + endPage);
        log.info("albaDTOPage.getTotalPages() : " + albaDTOPage.getTotalPages());
        log.info("albaDTOPage.getSize() : " + albaDTOPage.getSize());
        log.info("albaDTOPage.getTotalElements() : " + albaDTOPage.getTotalElements());
        log.info("albaDTOPage.hasNext() : " + albaDTOPage.hasNext());

        albaDTOPage.stream().map(AlbaDTO::getAlbaAddress).forEach(log::info);

        return albaListService.showAlbaEnd(pageable);
    }
}
