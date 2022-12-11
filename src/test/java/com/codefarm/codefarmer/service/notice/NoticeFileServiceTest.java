package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.entity.notice.NoticeFile;
import com.codefarm.codefarmer.repository.notice.NoticeFileRepository;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeFileServiceTest {
    @Autowired
    private NoticeFileService noticeFileService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;

//    공지 첨부파일 등록
    @Test
    public void addTest(){
        Optional<Notice> findNotice = noticeRepository.findById(51L);

        NoticeFileDTO noticeFileDTO01 = new NoticeFileDTO();
        noticeFileDTO01.setFileName("day01.txt");
        noticeFileDTO01.setFileSize(30L);
        noticeFileDTO01.setFileUploadPath("test");
        noticeFileDTO01.setFileUuid("abc111");
        noticeFileDTO01.setFileImageCheck(false);
        noticeFileDTO01.setNoticeId(11L);

        NoticeFileDTO noticeFileDTO02 = new NoticeFileDTO();
        noticeFileDTO02.setFileName("day02.txt");
        noticeFileDTO02.setFileSize(30L);
        noticeFileDTO02.setFileUploadPath("test");
        noticeFileDTO02.setFileUuid("abc111");
        noticeFileDTO02.setFileImageCheck(false);
        noticeFileDTO02.setNoticeId(11L);

        noticeFileService.noticeFileAdd(noticeFileDTO01);
        noticeFileService.noticeFileAdd(noticeFileDTO02);
    }

//    해당 공지 글의 첨부파일 조회
    @Test
    public void showAllTest(){
        noticeFileService.showAll(40L).stream().forEach(nf -> log.info("" + nf));
    }

//    해당 공지 글의 첨부파일 개수 조회
    @Test
    public void countTest(){
        List<Integer> countFiles = new ArrayList<>();

//        for (int i=0; i < noticeService.countByNotice(); i++) {
//            countFiles.add(noticeFileService.count(noticeService.showAll().get(i).getNoticeId()));
//        }
        countFiles.stream().forEach(c -> log.info("개수 : " + c));
        log.info("공지 글 개수 : " + noticeService.countByNotice());
    }

//    해당 공지 글의 첨부파일 삭제
    @Test
    public void removeTest(){
        noticeFileService.remove(40L);
    }

}
