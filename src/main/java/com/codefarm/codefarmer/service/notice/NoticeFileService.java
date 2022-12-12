package com.codefarm.codefarmer.service.notice;

import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.notice.NoticeFile;
import com.codefarm.codefarmer.repository.notice.NoticeFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NoticeFileService {
    private final NoticeFileRepository noticeFileRepository;

//    공지 첨부파일 저장
    public void noticeFileAdd(NoticeFileDTO noticeFileDTO){
        NoticeFile noticeFile = noticeFileDTO.toEntity();
        noticeFileRepository.save(noticeFile);
    }

//    공지 첨부파일 게시글 번호로 조회
    public List<NoticeFile> showAll(Long noticeId){
        return noticeFileRepository.findByNoticeId(noticeId);
    }

//    삭제
    public void remove(Long noticeId){
        noticeFileRepository.deleteByNoticeId(noticeId);
    }

}
