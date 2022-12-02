package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class MentorMenteeDTO {
    private Long mentorMenteeId;
    private Member mentorId;
    private Member menteeId;
    private Status menteeStatus;
    private String menteeComment;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public MentorMenteeDTO(Long mentorMenteeId, Member mentor, Member mentee, Status menteeStatus, String menteeComment, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.mentorMenteeId = mentorMenteeId;
        this.mentorId = mentor;
        this.menteeId = mentee;
        this.menteeStatus = menteeStatus;
        this.menteeComment = menteeComment;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public MentorMentee toEntity(){
        return MentorMentee.builder()
                .menteeComment(menteeComment)
                .menteeStatus(Status.WAITING)
                .build();
    }
}