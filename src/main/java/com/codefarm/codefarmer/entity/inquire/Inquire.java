package com.codefarm.codefarmer.entity.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquire extends Period {
    @Id @GeneratedValue
    private Long inquireId;
    @NotNull
    private String inquireQTitle;
    @NotNull @Column(length = 1000)
    private String inquireQContent;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status inquireStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Inquire(String inquireQTitle, String inquireQContent, Status inquireStatus, Member member) {
        this.inquireQTitle = inquireQTitle;
        this.inquireQContent = inquireQContent;
        this.inquireStatus = Status.WAITING;
        this.member = member;
    }

    public void update(InquireDTO inquireDTO){
        this.inquireStatus = inquireDTO.getInquireStatus();
    }

    @QueryProjection
    public Inquire(Long inquireId, String inquireQTitle, String inquireQContent, Status inquireStatus, Member member) {
        this.inquireId = inquireId;
        this.inquireQTitle = inquireQTitle;
        this.inquireQContent = inquireQContent;
        this.member = member;
    }
}
