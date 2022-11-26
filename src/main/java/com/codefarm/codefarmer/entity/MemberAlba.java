package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER_ALBA")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class MemberAlba extends Period{
    @Id @GeneratedValue
    private Long albaApplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBA_ID")
    private Alba alba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member memberId;

    @Enumerated(EnumType.STRING)
    private Status memberStatus;

    @Builder
    public MemberAlba(Status memberStatus) {
        this.memberStatus = memberStatus;
    }
}
