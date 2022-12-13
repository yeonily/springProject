package com.codefarm.codefarmer.domain.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class MemberAlbaDTO {
    private Long albaApplyId;
    private Alba alba;
    private Member member;
    private Long albaId;
    private Long memberId;
    private Status memberStatus;

    public MemberAlba toEntity(){
        return MemberAlba.builder()

                .memberStatus(memberStatus)
                .build();
    }

@QueryProjection
    public MemberAlbaDTO(Long albaId, Long memberId, Status memberStatus) {
        this.albaId = albaId;
        this.memberId = memberId;
        this.memberStatus = memberStatus;
    }
}
