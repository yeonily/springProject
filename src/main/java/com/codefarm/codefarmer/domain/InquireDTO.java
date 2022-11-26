package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Inquire;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class InquireDTO {
    private Long inquireId;
    private String inquireQTitle;
    private String inquireQContent;
    private String inquireAnswer;
    private Status inquireStatus;
    private Member member;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public InquireDTO(Long inquireId, String inquireQTitle, String inquireQContent, String inquireAnswer, Status inquireStatus, Member member, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.inquireId = inquireId;
        this.inquireQTitle = inquireQTitle;
        this.inquireQContent = inquireQContent;
        this.inquireAnswer = inquireAnswer;
        this.inquireStatus = inquireStatus;
        this.member = member;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public Inquire toEntity(){
        return Inquire.builder()
                .inquireQContent(inquireQContent)
                .inquireQTitle(inquireQTitle)
                .inquireStatus(inquireStatus)
                .build();
    }
}
