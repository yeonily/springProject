package com.codefarm.codefarmer.entity.notice;


import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.notice.QNotice.notice;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeTest {
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    공지 등록
    @Test
    public void noticeSaveTest(){
        Notice notice = new Notice();

//        notice.setNoticeTitle("공지 제목 TEST");
//        notice.setNoticeContent("공지 내용");

        noticeRepository.save(notice);
    }

//    공지 수정
    @Test
    public void noticeUpdateTest(){
        Notice notice = noticeRepository.findById(6L).get();

//        notice.setNoticeTitle("제목");
//        notice.setNoticeContent("테스트");

        noticeRepository.save(notice);
    }

//    공지 삭제
    @Test
    public void noticeDeleteTest(){
        noticeRepository.delete(noticeRepository.findById(5L).get());
    }


//    공지 디테일
    @Test
    public void noticeSelectTest(){
//        ( 1 )
        Optional<Notice> noticeDetail = noticeRepository.findById(6L);

        if(noticeDetail.isPresent()){
            log.info("ID : " + noticeDetail.get().getNoticeId());
            log.info("TITLE : " + noticeDetail.get().getNoticeTitle());
            log.info("CONTENT : " + noticeDetail.get().getNoticeContent());
            log.info("CreatedDATE : " + noticeDetail.get().getCreatedDate());
            log.info("UpdatedDATE : " + noticeDetail.get().getUpdatedDate());
        }
//        ( 2 )
        jpaQueryFactory.select(notice.noticeTitle, notice.noticeContent, notice.createdDate).from(notice)
                .where(notice.noticeId.eq(6L))
                .fetch()
                .stream().map(Notice -> Notice.toString()).forEach(log::info);

    }
}
