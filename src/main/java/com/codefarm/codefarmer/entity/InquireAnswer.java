package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRE_ANSWER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class InquireAnswer extends Period{
    @Id @GeneratedValue
    private Long inquireAnswerId;
    private String inquireAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRE_ID")
    private Inquire inquire;

    @Builder
    public InquireAnswer(String inquireAnswer) {
        this.inquireAnswer = inquireAnswer;
    }
}
