package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.NoticeDTO;
import com.codefarm.codefarmer.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeTest {
    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void noticeSaveTest(){
        NoticeDTO noticeDTO = new NoticeDTO();

        noticeDTO.setNoticeTitle("공지 제목");
        noticeDTO.setNoticeContent("공지 내용");

        Notice notice = noticeDTO.toEntity();
        noticeRepository.save(notice);
    }

    @Test
    public void noticeUpdateTest(){
        NoticeDTO noticeDTO = new NoticeDTO();
        Notice notice = noticeRepository.findById(6L).get();

        noticeDTO.setNoticeTitle("제목임");
        noticeDTO.setNoticeContent("수정테스트");
        noticeDTO.setNoticeId(notice.getNoticeId());

        notice.update(noticeDTO);
    }

    @Test
    public void noticeDeleteTest(){
        noticeRepository.delete(noticeRepository.findById(5L).get());
    }

    @Test
    public void noticeSelectTest(){
        Optional<Notice> noticeDetail = noticeRepository.findById(6L);

        if(noticeDetail.isPresent()){
            log.info("ID : " + noticeDetail.get().getNoticeId());
            log.info("TITLE : " + noticeDetail.get().getNoticeTitle());
            log.info("CONTENT : " + noticeDetail.get().getNoticeContent());
            log.info("CreatedDATE : " + noticeDetail.get().getCreatedDate());
            log.info("UpdatedDATE : " + noticeDetail.get().getUpdatedDate());
        }
    }
}
