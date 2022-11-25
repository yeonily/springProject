package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Alba;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MemberAlba;
import com.codefarm.codefarmer.type.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@NoArgsConstructor
@Data
public class MemberAlbaDTO {
    private Long albaApplyId;
    private Alba alba;
    private Member memberId;
    private Status memberStatus;

    public MemberAlba toEntity(){
        return MemberAlba.builder()
                .memberStatus(memberStatus)
                .build();
    }
}
