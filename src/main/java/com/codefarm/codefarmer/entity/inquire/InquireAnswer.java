package com.codefarm.codefarmer.entity.inquire;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRE_ANSWER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquireAnswer extends Period {
    @Id @GeneratedValue
    private Long inquireAnswerId;
    @NotNull @Column(length = 1000)
    private String inquireAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRE_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Inquire inquire;

    public void changeInquire(Inquire inquire){
        this.inquire = inquire;
    }

    public void update(InquireAnswerDTO inquireAnswerDTO){
        this.inquireAnswer = inquireAnswerDTO.getInquireAnswer();
    }

    @Builder
    public InquireAnswer(String inquireAnswer) {
        this.inquireAnswer = inquireAnswer;
    }

    @QueryProjection
    public InquireAnswer(String inquireAnswer, Inquire inquire) {
        this.inquireAnswer = inquireAnswer;
        this.inquire = inquire;
    }
}
