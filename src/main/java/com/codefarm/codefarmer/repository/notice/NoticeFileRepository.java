package com.codefarm.codefarmer.repository.notice;

import com.codefarm.codefarmer.entity.notice.NoticeFile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.model.IAttribute;

import java.util.List;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {

//    해당 공지글 번호로 첨부파일 조회
    @EntityGraph(attributePaths = "notice")
    @Query("select nf from NoticeFile nf where nf.notice.noticeId = :noticeId")
    public List<NoticeFile> findByNoticeId(Long noticeId);

//    해당 공지글 번호로 첨부파일 개수 조회
    @Query("select count(nf.fileId) from NoticeFile nf where nf.notice.noticeId = :noticeId")
    public int countByNoticeId(Long noticeId);

//    해당 공지글 번호로 첨부파일 삭제
    @Transactional
    @Modifying
    @Query("delete from NoticeFile nf where nf.notice.noticeId in :noticeId")
    public void deleteByNoticeId(Long noticeId);
}
