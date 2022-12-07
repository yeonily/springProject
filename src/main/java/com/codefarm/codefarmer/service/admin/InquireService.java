package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.inquire.InquireRepository;
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
}
