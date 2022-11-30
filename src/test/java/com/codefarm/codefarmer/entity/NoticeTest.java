package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.NoticeDTO;
import com.codefarm.codefarmer.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.QNotice.notice;

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
        NoticeDTO noticeDTO = new NoticeDTO();

        noticeDTO.setNoticeTitle("공지 제목");
        noticeDTO.setNoticeContent("공지 내용");

        Notice notice = noticeDTO.toEntity();
        noticeRepository.save(notice);
    }

//    공지 수정
    @Test
    public void noticeUpdateTest(){
        NoticeDTO noticeDTO = new NoticeDTO();
        Notice notice = noticeRepository.findById(6L).get();

        noticeDTO.setNoticeTitle("제목임");
        noticeDTO.setNoticeContent("수정테스트");
        noticeDTO.setNoticeId(notice.getNoticeId());

        notice.update(noticeDTO);
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
