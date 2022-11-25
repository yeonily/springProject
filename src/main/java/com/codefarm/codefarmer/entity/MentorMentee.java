package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR_MENTEE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class MentorMentee extends Period{
    @Id @GeneratedValue
    private Long mentorMenteeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @Column(name = "MENTOR_ID")
    private Member mentor;

    //확인필요!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @Column(name = "MENTEE_ID")
    private Member mentee;

    @Enumerated(EnumType.STRING)
    private Status menteeStatus;
    @Column(nullable = false)
    private String menteeComment;

    @Builder
    public MentorMentee(Status menteeStatus, String menteeComment) {
        this.menteeStatus = menteeStatus;
        this.menteeComment = menteeComment;
    }
}
