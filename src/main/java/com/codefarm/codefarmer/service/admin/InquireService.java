package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.inquire.InquireRepository;
import com.codefarm.codefarmer.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquireService {
    private final InquireRepository inquireRepository;
    private final InquireAnswerRepository inquireAnswerRepository;

//    문의 등록
    @Transactional
    public void inquireAdd(InquireDTO inquireDTO) {
        Inquire inquire = inquireDTO.toEntity();
        inquire.changeMember(inquireDTO.getMember());
        log.info("문의 --> " + inquire);

        inquireRepository.save(inquire);
    }

//    문의 목록
    public List<Inquire> inquireShowAll(){
        return inquireRepository.findAll();
    }

//    문의글 찾기
    public Inquire showInquireOne(Long inquireId){return inquireRepository.findById(inquireId).get();}

//    문의글 개수
    public int countInquire(){return inquireRepository.countByInquire();}

//    문의 답변이 달리면 상태 변경
    @Transactional
    public void statusUpdate(Long inquireId, Status status) {
        inquireRepository.updateStatues(inquireId, status);
    }

//    문의 답변 등록
    @Transactional
    public void answerAdd(InquireAnswerDTO inquireAnswerDTO) {
        InquireAnswer inquireAnswer = inquireAnswerDTO.toEntity();
        inquireAnswer.changeInquire(inquireAnswerDTO.getInquire());
        log.info("문의답변 --> " + inquireAnswer);

        inquireAnswerRepository.save(inquireAnswer);
    }

//    문의 답변 수정
    @Transactional
    public void answerUpdate(InquireAnswerDTO inquireAnswerDTO) {
        InquireAnswer inquireAnswer = inquireAnswerRepository.findById(inquireAnswerDTO.getInquireAnswerId()).get();
        inquireAnswer.update(inquireAnswerDTO);
        inquireAnswerRepository.save(inquireAnswer);
    }

//    문의 답변 문의 글 번호로 찾기
    public InquireAnswer answerCheck(Inquire inquire){return inquireAnswerRepository.findByInquireAnswer(inquire);}

//    문의 답변 번호로  찾기
    public InquireAnswer answerShowOne(Long inquireAnswerId){return inquireAnswerRepository.findById(inquireAnswerId).get();}
}
