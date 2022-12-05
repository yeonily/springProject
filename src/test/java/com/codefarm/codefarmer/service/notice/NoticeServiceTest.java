package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import com.codefarm.codefarmer.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        Notice notice = new Notice();

//        List<NoticeFile> files = new ArrayList<>();
//        NoticeFile noticeFile01 = new NoticeFile();
//        noticeFile01.setFileName("day01.txt");
//        noticeFile01.setFileSize(30L);
//        noticeFile01.setFileUploadPath("test");
//        noticeFile01.setFileUuid("abc111");
//        noticeFile01.setNotice(noticeDTO.getNoticeId());

//        NoticeFile noticeFile02 = new NoticeFile();
//        noticeFile02.setFileName("day02.txt");
//        noticeFile02.setFileSize(30L);
//        noticeFile02.setFileUploadPath("test");
//        noticeFile02.setFileUuid("abc111");
//        noticeFile02.setBoardNumber(269L);

//        files.add(noticeFile01);
//        files.add(fileVO2);

        notice.setNoticeTitle("공지 TEST");
        notice.setNoticeContent("서비스 내용");
//        noticeDTO.setNoticeFiles(files);

        noticeService.register(notice);
    }

//    공지 수정
    @Test
    public void upateTest(){
        Notice notice = noticeService.showOne(40L);
        notice.setNoticeContent("수정임");

        noticeService.update(notice);
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
