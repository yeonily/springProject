package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Alba;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MemberProgram;
import com.codefarm.codefarmer.entity.Program;
import com.codefarm.codefarmer.type.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@NoArgsConstructor
@Data
public class MemberProgramDTO {
    private Long programApplyId;
    private Program program;
    private Member member;
    private int programApplyCount;
    private int programPayment;

    public MemberProgram toEntity(){
        return MemberProgram.builder()
                .programApplyCount(programApplyCount)
                .programPayment(programPayment)
                .build();
    }
}