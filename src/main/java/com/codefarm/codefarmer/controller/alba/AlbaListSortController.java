package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kind/*")
public class AlbaListSortController {

    @Autowired
    private AlbaListService albaListService;

//    시급순 버튼
    @PostMapping("/payList")
    public List<AlbaDTO> showListByHighPay(@PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        log.info("시급 들어옴");
        albaListService.showListByHighPay(pageable).forEach(a -> log.info(a.toString()));
        return albaListService.showListByHighPay(pageable);
    }

//    최신순 버튼
    @PostMapping("/newList")
    public List<AlbaDTO> showByRecent(@PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        log.info("최신 들어옴");
        albaListService.showByRecent(pageable).forEach(a->log.info(a.toString()));
        return albaListService.showByRecent(pageable);
    }

//    모집순 버튼
    @PostMapping("/collectList")
    public List<AlbaDTO> showListByEndDate(@PageableDefault( size = 10, sort = "AlbaId", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
        log.info("모집 들어옴");
        albaListService.showListByEndDate(pageable).forEach(a->log.info(a.toString()));
        return albaListService.showListByEndDate(pageable);
    }

}
