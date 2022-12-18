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
    private Long mentorId;
    private Long menteeId;
    private Status menteeStatus;
//    private String menteeStatus;
    private String menteeComment;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private String mentorNickName;
    private String menteeNickName;
    private String menteeName;
    private String menteePhone;

    @QueryProjection
    public MentorMenteeDTO(Long mentorMenteeId, Long menteeId, String menteeName, String menteeNickName, String menteePhone) {
        this.mentorMenteeId = mentorMenteeId;
        this.menteeName = menteeName;
        this.menteeId = menteeId;
        this.menteeNickName = menteeNickName;
        this.menteePhone = menteePhone;
    }

    @QueryProjection
    public MentorMenteeDTO(Long mentorMenteeId, Long mentorId, Long menteeId, Status menteeStatus, String menteeComment, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.mentorMenteeId = mentorMenteeId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.menteeStatus = menteeStatus;
        this.menteeComment = menteeComment;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    @QueryProjection
    public MentorMenteeDTO(Long mentorMenteeId, Long mentorId, Long menteeId, Status menteeStatus, LocalDateTime updateDate, String mentorNickName, String menteeNickName, String menteeComment) {
        this.mentorMenteeId = mentorMenteeId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.menteeStatus = menteeStatus;
        this.updateDate = updateDate;
        this.mentorNickName = mentorNickName;
        this.menteeNickName = menteeNickName;
        this.menteeComment = menteeComment;
    }

    public MentorMentee toEntity(){
        return MentorMentee.builder()
                .menteeComment(menteeComment)
                .menteeStatus(Status.WAITING)
                .build();
    }
}