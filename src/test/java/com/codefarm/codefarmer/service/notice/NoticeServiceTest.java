package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.entity.notice.NoticeFile;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import com.codefarm.codefarmer.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;

//    공지 등록
    @Test
    public void registerTest(){
        NoticeDTO noticeDTO = new NoticeDTO();

//        List<NoticeFileDTO> files = new ArrayList<>();
//        NoticeFileDTO noticeFile01 = new NoticeFileDTO();
//        noticeFile01.setFileName("day01.txt");
//        noticeFile01.setFileSize(30L);
//        noticeFile01.setFileUploadPath("test");
//        noticeFile01.setFileUuid("abc111");
//        noticeFile01.setNoticeId(noticeDTO.getNoticeId());
//
//        NoticeFileDTO noticeFile02 = new NoticeFileDTO();
//        noticeFile02.setFileName("day02.txt");
//        noticeFile02.setFileSize(30L);
//        noticeFile02.setFileUploadPath("test");
//        noticeFile02.setFileUuid("abc111");
//        noticeFile02.setNoticeId(noticeDTO.getNoticeId());
//
//        files.add(noticeFile01);
//        files.add(noticeFile02);

        noticeDTO.setNoticeTitle("공지 TEST");
        noticeDTO.setNoticeContent("서비스 내용");
//        noticeDTO.setFiles(files);

        noticeService.register(noticeDTO);
    }

//    공지 수정
    @Test
    public void upateTest(){
        Notice notice = noticeRepository.findById(49L).get();
        notice.setNoticeContent("수정임");

        noticeRepository.save(notice);
    }

//    공지 삭제
    @Test
    public void removeTest(){
        log.info("공지 번호" + noticeService.remove(4L));
    }

//    공지 목록
    @Test
    public void selectAllTest(Pageable pageable) {
        noticeService.showAll(pageable).stream().forEach(n -> log.info("" + n));
    }

//    공지 디테일
    @Test
    public void selectOneTest(){
        log.info("" + noticeService.showOne(40L));
    }

//    공지 글 개수
    @Test
    public void countByNoticeTest() {
        log.info("" + noticeService.countByNotice());
    }

}
