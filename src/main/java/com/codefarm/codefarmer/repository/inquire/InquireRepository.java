package com.codefarm.codefarmer.repository.inquire;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InquireRepository extends JpaRepository<Inquire, Long> {
//    (제목+내용) + 페이징
    public Page<Inquire> findByInquireQTitleContainingOrInquireQContentContaining(String inquireQTitle, String inquireQContent, Pageable pageable);
//    검색(제목) + 페이징
    public Page<Inquire> findByInquireQTitleContaining(String inquireQTitle, Pageable pageable);
//    검색(내용) + 페이징
    public Page<Inquire> findByInquireQContentContaining(String inquireQContent, Pageable pageable);


//    문의 글 개수
    @Query("select count(i) from Inquire i")
    public int countByInquire();

//    문의 답변 상태 업데이트
    @Transactional
    @Modifying
    @Query("update Inquire i set i.inquireStatus= :status where i.inquireId in :inquireId")
    public void updateStatues(@Param("inquireId")Long inquireId, @Param("status") Status status);

}
