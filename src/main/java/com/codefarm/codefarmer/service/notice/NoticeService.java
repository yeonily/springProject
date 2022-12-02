package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

//    공지 글 작성
    public void noticeAdd(Notice notice){
        noticeRepository.save(notice);
    }

//    공지 글 수정
    public void noticeUpdate(Notice notice){
        Notice noticeModify = noticeRepository.findById(notice.getNoticeId()).get();
        noticeRepository.save(noticeModify);
    }

//    공지 글 삭제
    public Long noticeDelete(Long noticeId){
        noticeRepository.delete(noticeRepository.findById(noticeId).get());
        return noticeId;
    }

//    공지 목록
    public List<Notice> noticeSelectAll(){
        return noticeRepository.findAll();
    }

//    공지 디테일 보기
    public Notice noticeSelectOne(Long noticeId){
        return noticeRepository.findById(noticeId).get();
    }

}
