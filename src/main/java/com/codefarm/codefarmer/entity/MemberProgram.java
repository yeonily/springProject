package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.ProgramStatus;
import com.sun.istack.NotNull;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROGRAM_ID")
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ColumnDefault("1")
    private int programApplyCount;
    @ColumnDefault("0")
    private int programPayment;

    @Builder
    public MemberProgram(ProgramStatus programStatus, int programApplyCount, int programPayment) {
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
    }
}
