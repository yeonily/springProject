package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR_MENTEE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorMentee extends Period {
    @Id @GeneratedValue
    private Long mentorMenteeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTOR_ID", nullable = false)
    private Member mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTEE_ID", nullable = false)
    private Member mentee;


    @NotNull
    @Enumerated(EnumType.STRING)
    private Status menteeStatus;

    @NotNull
    private String menteeComment;

    public void changeMentor(Member mentor){
        this.mentor = mentor;
    }

    public void changeMentee(Member mentee){
        this.mentee = mentee;
    }

    @Builder
    public MentorMentee(Status menteeStatus, String menteeComment) {
        this.menteeStatus = menteeStatus;
        this.menteeComment = menteeComment;
    }
}
