package com.codefarm.codefarmer.entity.program;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.type.ProgramStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_MEMBER_PROGRAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProgram extends Period {
    @Id @GeneratedValue
    private Long programApplyId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROGRAM_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ColumnDefault("1")
    private int programApplyCount;
    @ColumnDefault("0")
    private int programPayment;

    @NotNull
    private String programApplyName;

    @NotNull
    private String programApplyPhoneNum;

    @NotNull
    private String programApplyEmail;

    @NotNull
    private String programApplyLocation;

    @NotNull
    private LocalDateTime programApplyBirth;

    public void changeMember(Member member){
        this.member = member;
    }
    public void changeProgram(Program program){ this.program = program; }

   /* @Builder
  *//*  public MemberProgram(ProgramStatus programStatus, int programApplyCount, int programPayment ,String pro) {
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;

    }*/

    @Builder
    public MemberProgram(ProgramStatus programStatus, int programApplyCount, int programPayment, String programApplyName, String programApplyPhoneNum, String programApplyEmail, String programApplyLocation, LocalDateTime programApplyBirth,Member member , Program program) {
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
        this.programApplyName = programApplyName;
        this.programApplyPhoneNum = programApplyPhoneNum;
        this.programApplyEmail = programApplyEmail;
        this.programApplyLocation = programApplyLocation;
        this.programApplyBirth = programApplyBirth;
        this.member = member;
        this.program = program;
    }

}
