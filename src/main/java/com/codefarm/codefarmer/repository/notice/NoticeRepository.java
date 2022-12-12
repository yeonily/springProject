package com.codefarm.codefarmer.repository.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    (제목+내용) + 페이징
    public Page<Notice> findByNoticeTitleContainingAndNoticeContentContaining(String noticeTitle, String noticeContent, Pageable pageable);
//    검색(제목) + 페이징
    public Page<Notice> findByNoticeTitleContaining(String noticeTitle, Pageable pageable);
//    검색(내용) + 페이징
    public Page<Notice> findByNoticeContentContaining(String noticeContent, Pageable pageable);

//    공지 번호 순으로 정렬
    @Query("select n from Notice n order by n.noticeId desc")
    public List<Notice> OrderByNoticeId();

//    공지 글 개수
    @Query("select count(nf) from Notice nf")
    public int countByNotice();

//    조회수 1씩 증가
    @Transactional
    @Modifying
    @Query("update Notice n set n.noticeViewCount=n.noticeViewCount+1 where n.noticeId in :noticeId")
    public void updateViewCount(Long noticeId);
}
