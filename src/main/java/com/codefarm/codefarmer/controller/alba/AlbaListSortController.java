package com.codefarm.codefarmer.controller.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AlbaDTO> showListByHighPay() throws Exception{
        log.info("시급 들어옴");
        albaListService.showListByHighPay().forEach(a -> log.info(a.toString()));
        return albaListService.showListByHighPay();
    }

//    최신순 버튼
    @PostMapping("/newList")
    public List<AlbaDTO> showListByRecent() throws Exception{
        log.info("최신 들어옴");
        albaListService.showListByRecent().forEach(a->log.info(a.toString()));
        return albaListService.showListByRecent();
    }

//    모집순 버튼
    @PostMapping("/collectList")
    public List<AlbaDTO> showListByGatheringList() throws Exception{
        log.info("모집 들어옴");
        albaListService.showListByGatheringList().forEach(a->log.info(a.toString()));
        return albaListService.showListByGatheringList();
    }

}
