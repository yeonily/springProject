package com.codefarm.codefarmer.repository.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InquireAnswerRepository extends JpaRepository<InquireAnswer, Long> {
//    답변 유무 체크
    @Query("select ia from InquireAnswer ia where ia.inquire = :inquire")
    public InquireAnswer findByInquireAnswer(Inquire inquire);
}
