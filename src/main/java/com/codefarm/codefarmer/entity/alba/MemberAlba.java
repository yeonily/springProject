package com.codefarm.codefarmer.entity.alba;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.type.Status;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER_ALBA")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAlba extends Period {
    @Id @GeneratedValue
    private Long albaApplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBA_ID")
    private Alba alba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Enumerated(EnumType.STRING)
    private Status memberStatus;

    public void changeMember(Member member){
        this.member = member;
    }
    public void changeAlba(Alba alba){ this.alba = alba; }


    @Builder
    public MemberAlba(Status memberStatus) {
        this.memberStatus = memberStatus;
    }
}
