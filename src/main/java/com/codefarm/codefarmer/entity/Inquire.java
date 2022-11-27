package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquire extends Period{
    @Id @GeneratedValue
    private Long inquireId;
    @NotNull
    private String inquireQTitle;
    @NotNull
    private String inquireQContent;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status inquireStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Inquire(String inquireQTitle, String inquireQContent, Status inquireStatus) {
        this.inquireQTitle = inquireQTitle;
        this.inquireQContent = inquireQContent;
        this.inquireStatus = inquireStatus;
    }
}
