package com.codefarm.codefarmer.domain.inquire;

import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class InquireDTO {
    private Long inquireId;
    private String inquireQTitle;
    private String inquireQContent;
    private Status inquireStatus;
    private Member member;
    private Long memberId;
    private LocalDateTime createdDate;

    @QueryProjection
    public InquireDTO(Long inquireId, String inquireQTitle, String inquireQContent, Status inquireStatus, Long memberId, LocalDateTime createdDate) {
        this.inquireId = inquireId;
        this.inquireQTitle = inquireQTitle;
        this.inquireQContent = inquireQContent;
        this.inquireStatus = inquireStatus;
        this.memberId = memberId;
        this.createdDate = createdDate;
    }

    public Inquire toEntity(){
        return Inquire.builder()
                .inquireQContent(inquireQContent)
                .inquireQTitle(inquireQTitle)
                .member(member)
                .build();
    }
}
