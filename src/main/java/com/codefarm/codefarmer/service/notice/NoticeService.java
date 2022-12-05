package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

//    공지 글 작성
    public void register(Notice notice){
        noticeRepository.save(notice);
    }

//    공지 글 수정
    public void update(Notice notice){
        Notice noticeModify = noticeRepository.findById(notice.getNoticeId()).get();
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
    public Page<Notice> showAll(Pageable pageable){
        return noticeRepository.findAll(pageable);
    }

//    공지 디테일 보기
    public Notice showOne(Long noticeId){
        return noticeRepository.findById(noticeId).get();
    }

//    공지 글 개수
    public int countByNotice() {return noticeRepository.countByNotice();}
}
