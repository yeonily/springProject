package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.inquire.InquireCustomRepository;
import com.codefarm.codefarmer.repository.inquire.InquireRepository;
import com.codefarm.codefarmer.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquireService {
    private final InquireRepository inquireRepository;
    private final InquireAnswerRepository inquireAnswerRepository;
    private final InquireCustomRepository inquireCustomRepository;

//    문의 등록
    @Transactional
    public void inquireAdd(InquireDTO inquireDTO) {
        Inquire inquire = inquireDTO.toEntity();
        inquire.changeMember(inquireDTO.getMember());

        inquireRepository.save(inquire);
    }

//    문의 목록
    public Page<Inquire> inquireShowAll(Pageable pageable, String keyword, String inquireQTitle, String inquireQContent, String memeberNickname){
        List<Inquire> inquires = inquireCustomRepository.findByInquireLikeMemberNickname(memeberNickname);
        final int total = inquireCustomRepository.countByMemberNickname(memeberNickname);
        final int start = (int)pageable.getOffset();
        final int end = (start + pageable.getPageSize()) < total ? (start + pageable.getPageSize()) : total;

        if (keyword.equals("t")){
            return inquireRepository.findByInquireQTitleContaining(inquireQTitle, pageable);
        } else if (keyword.equals("c")){
            return inquireRepository.findByInquireQContentContaining(inquireQContent, pageable);
        } else if (keyword.equals("w")){
            return new PageImpl<>(inquires.subList(start, end), pageable, inquires.size());
        } else {
            return inquireRepository.findByInquireQTitleContainingOrInquireQContentContaining(inquireQTitle, inquireQContent, pageable);
        }
    }

//    작성자로 검색했을 때 개수
    public int countByNickname (String memeberNickname){
        int total = inquireCustomRepository.countByMemberNickname(memeberNickname);
        return total%10 == 0 ? (total/10) : ((total/10) + 1);
    }

//    문의글 찾기
    public Inquire showInquireOne(Long inquireId){return inquireRepository.findById(inquireId).get();}

//    문의글 개수
    public int countByInquire(){return inquireRepository.countByInquire();}

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


//  문의 답변 보기
    public Optional<InquireAnswer> showAnswer(Long inquireId){
        return inquireAnswerRepository.findByInquireId(inquireId);
    }
}
