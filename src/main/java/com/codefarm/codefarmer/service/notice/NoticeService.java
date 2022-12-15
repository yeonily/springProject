package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.domain.notice.QNoticeDTO;
import com.codefarm.codefarmer.domain.notice.QNoticeFileDTO;
import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.entity.notice.QNotice;
import com.codefarm.codefarmer.repository.notice.NoticeFileRepository;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;
import static com.codefarm.codefarmer.entity.notice.QNotice.notice;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final NoticeFileService noticeFileService;
    private final JPAQueryFactory jpaQueryFactory;

//    공지 글 작성
    public void register(NoticeDTO noticeDTO){
        Notice notice = noticeDTO.toEntity();

        if (noticeDTO.getNoticeFiles() != null){
            notice.changeFiles(noticeDTO.getNoticeFiles());
            notice.getNoticeFiles().stream().map(nf -> noticeFileRepository.save(nf));
        }
        noticeRepository.save(notice);
    }

//    공지 글 수정
    public void update(NoticeDTO noticeDTO){
        Notice noticeModify = noticeRepository.findById(noticeDTO.getNoticeId()).get();

        noticeFileService.remove(noticeDTO.getNoticeId());
        if (noticeDTO.getNoticeFiles() != null){
            for (int i = 0; i < noticeDTO.getNoticeFiles().size(); i++ ) {
                noticeModify.changeFiles(noticeDTO.getNoticeFiles());
                noticeModify.getNoticeFiles().stream().map(nf -> noticeFileRepository.save(nf));
            }
        }

        noticeModify.update(noticeDTO);
        noticeRepository.save(noticeModify);
    }

//    공지 조회수 증가
    public void updateViewCount(Long noticeId){
        noticeRepository.updateViewCount(noticeId);
    }

//    공지 글 삭제
    public Long remove(Long noticeId){
        noticeRepository.delete(noticeRepository.findById(noticeId).get());
        return noticeId;
    }

//    공지 목록
@Transactional(readOnly = true)
    public Page<Notice> showAll(Pageable pageable){
        return noticeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Notice> searchShowAll(Pageable pageable, String keyword, String noticeTitle, String noticeContent){
        if (keyword.equals("t")){
            return noticeRepository.findByNoticeTitleContaining(noticeTitle, pageable);
        } else if (keyword.equals("c")){
            return noticeRepository.findByNoticeContentContaining(noticeContent, pageable);
        } else {
            return noticeRepository.findByNoticeTitleContainingAndNoticeContentContaining(noticeTitle, noticeContent, pageable);
        }
    }

//    공지 디테일 보기
    public Notice showOne(Long noticeId){
        return noticeRepository.findById(noticeId).get();
    }

//    공지 글 개수
    public int countByNotice() {return noticeRepository.countByNotice();}

    public List<NoticeDTO> showNoticeByRecentThree(){
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.noticeViewCount,
                notice.createdDate,
                notice.updatedDate
        )).from(notice)
          .orderBy(notice.updatedDate.desc())
          .limit(3)
          .fetch();
    }
}
