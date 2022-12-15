package com.codefarm.codefarmer.domain.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    private String memberName;
    private String memberEmail;

    private LocalDateTime albaWorkDate;
    private String albaMainTitle;
    private int albaPrice;
    private String albaAddress;

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

    @QueryProjection
    public MemberAlbaDTO(Long albaApplyId, Long memberId, Status memberStatus, String memberName, String memberEmail, Long albaId) {
        this.albaApplyId = albaApplyId;
        this.memberId = memberId;
        this.memberStatus = memberStatus;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.albaId = albaId;
    }

    @QueryProjection
    public MemberAlbaDTO(Long albaApplyId, Long albaId, Status memberStatus, LocalDateTime albaWorkDate, String albaMainTitle, int albaPrice, String albaAddress) {
        this.albaApplyId = albaApplyId;
        this.albaId = albaId;
        this.memberStatus = memberStatus;
        this.albaWorkDate = albaWorkDate;
        this.albaMainTitle = albaMainTitle;
        this.albaPrice = albaPrice;
        this.albaAddress = albaAddress;
    }
}
