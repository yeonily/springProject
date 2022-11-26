package com.codefarm.codefarmer.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER_PROGRAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProgram extends Period{
    @Id @GeneratedValue
    private Long programApplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROGRAM_ID")
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ColumnDefault("1")
    private int programApplyCount;
    private int programPayment;

    @Builder
    public MemberProgram(int programApplyCount, int programPayment) {
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
    }
}
