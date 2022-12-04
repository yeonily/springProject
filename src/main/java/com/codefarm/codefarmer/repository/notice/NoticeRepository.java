package com.codefarm.codefarmer.repository.notice;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    공지 번호 순으로 정렬
    @Query("select n from Notice n order by n.noticeId desc")
    public List<Notice> OrderByNoticeId();

//    공지 글 개수
    @Query("select count(nf) from Notice nf")
    public int countByNotice();


//    해당 공지글 번호로 첨부파일 삭제
    @Transactional
    @Modifying
    @Query("update Notice n set n.noticeViewCount=n.noticeViewCount+1 where n.noticeId in :noticeId")
    public void updateViewCount(Long noticeId);
}
