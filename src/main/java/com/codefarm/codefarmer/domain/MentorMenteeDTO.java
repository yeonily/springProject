package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MentorBoard;
import com.codefarm.codefarmer.entity.MentorMentee;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class MentorMenteeDTO {
    private Long mentorMenteeId;
    private Member mentor;
    private Member mentee;
    private Status menteeStatus;
    private String menteeComment;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public MentorMenteeDTO(Long mentorMenteeId, Member mentor, Member mentee, Status menteeStatus, String menteeComment, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.mentorMenteeId = mentorMenteeId;
        this.mentor = mentor;
        this.mentee = mentee;
        this.menteeStatus = menteeStatus;
        this.menteeComment = menteeComment;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public MentorMentee toEntity(){
        return MentorMentee.builder()
                .menteeComment(menteeComment)
                .menteeStatus(menteeStatus)
                .build();
    }
}