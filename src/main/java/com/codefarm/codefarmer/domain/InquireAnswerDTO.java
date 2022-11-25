package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Inquire;
import com.codefarm.codefarmer.entity.InquireAnswer;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class InquireAnswerDTO {
    private Long inquireAnswerId;
    private String inquireAnswer;


    public InquireAnswer toEntity(){
        return InquireAnswer.builder()
                .inquireAnswer(inquireAnswer)
                .build();
    }
}
