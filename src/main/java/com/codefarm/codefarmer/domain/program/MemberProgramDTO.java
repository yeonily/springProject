package com.codefarm.codefarmer.domain.program;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.type.ProgramStatus;
import com.codefarm.codefarmer.type.ProgramType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class MemberProgramDTO {
    private Long programApplyId;
    private Program program;
    private Member member;
    private ProgramStatus programStatus;
    private int programApplyCount;
    private int programPayment;
    private String programApplyName;
    private String programApplyPhoneNum;
    private String programApplyEmail;
    private String programApplyLocation;
    private LocalDateTime programApplyBirth;
    private LocalDateTime updateDate;
    private String programTitle;

    private Long programId;
    private ProgramType programType;
    private int programPrice;


    public MemberProgram toEntity(){
        return MemberProgram.builder()
                .programApplyCount(programApplyCount)
                .programPayment(programPayment)
                .programStatus(programStatus)
                .programApplyName(programApplyName)
                .programApplyBirth(programApplyBirth)
                .programApplyEmail(programApplyEmail)
                .programApplyLocation(programApplyLocation)
                .programApplyPhoneNum(programApplyPhoneNum)
                .build();
    }

    @QueryProjection
    public MemberProgramDTO(Long programApplyId, Program program, Member member, ProgramStatus programStatus, int programApplyCount, int programPayment, String programApplyName, String programApplyPhoneNum, String programApplyEmail, String programApplyLocation, LocalDateTime programApplyBirth) {
        this.programApplyId = programApplyId;
        this.program = program;
        this.member = member;
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
        this.programApplyName = programApplyName;
        this.programApplyPhoneNum = programApplyPhoneNum;
        this.programApplyEmail = programApplyEmail;
        this.programApplyLocation = programApplyLocation;
        this.programApplyBirth = programApplyBirth;
    }

    @QueryProjection
    public MemberProgramDTO(Long programApplyId, ProgramStatus programStatus, int programApplyCount, int programPayment, String programApplyName, String programApplyPhoneNum, String programApplyEmail, String programApplyLocation, LocalDateTime programApplyBirth) {
        this.programApplyId = programApplyId;
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
        this.programApplyName = programApplyName;
        this.programApplyPhoneNum = programApplyPhoneNum;
        this.programApplyEmail = programApplyEmail;
        this.programApplyLocation = programApplyLocation;
        this.programApplyBirth = programApplyBirth;
    }

    @QueryProjection
    public MemberProgramDTO(Long programApplyId, ProgramStatus programStatus, int programPayment, LocalDateTime updateDate, String programTitle) {
        this.programApplyId = programApplyId;
        this.programStatus = programStatus;
        this.programPayment = programPayment;
        this.updateDate = updateDate;
        this.programTitle = programTitle;
    }

    @QueryProjection
    public MemberProgramDTO(Long programApplyId, ProgramStatus programStatus, int programApplyCount, int programPayment, String programApplyName, String programApplyPhoneNum, String programApplyEmail, String programApplyLocation, LocalDateTime programApplyBirth, String programTitle, ProgramType programType, Long programId, int programPrice) {
        this.programApplyId = programApplyId;
        this.programStatus = programStatus;
        this.programApplyCount = programApplyCount;
        this.programPayment = programPayment;
        this.programApplyName = programApplyName;
        this.programApplyPhoneNum = programApplyPhoneNum;
        this.programApplyEmail = programApplyEmail;
        this.programApplyLocation = programApplyLocation;
        this.programApplyBirth = programApplyBirth;
        this.programTitle = programTitle;
        this.programType = programType;
        this.programId = programId;
        this.programPrice = programPrice;
    }
}