package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Inquire extends Period{
    @Id @GeneratedValue
    private Long inquireId;
    @Column(nullable = false)
    private String inquireQTitle;
    @Column(nullable = false)
    private String inquireQContent;
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
