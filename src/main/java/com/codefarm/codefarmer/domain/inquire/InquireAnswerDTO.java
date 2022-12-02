package com.codefarm.codefarmer.domain.inquire;

import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class InquireAnswerDTO {
    private Long inquireAnswerId;
    private String inquireAnswer;
    private Inquire inquire;

    public InquireAnswer toEntity(){
        return InquireAnswer.builder()
                .inquireAnswer(inquireAnswer)
                .build();
    }
}
