package com.codefarm.codefarmer.domain.program;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.type.ProgramStatus;
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

<<<<<<< HEAD

=======
//    @QueryProjection
//    public MemberProgramDTO(Program program, Member member, ProgramStatus programStatus, int programApplyCount, int programPayment) {
//        this.program = program;
//        this.member = member;
//        this.programStatus = programStatus;
//        this.programApplyCount = programApplyCount;
//        this.programPayment = programPayment;
//    }
>>>>>>> origin/master

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
}