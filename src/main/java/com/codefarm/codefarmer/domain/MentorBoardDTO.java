package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MentorBoard;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@NoArgsConstructor
@Data
public class MentorBoardDTO {
    private Long mentorBoardId;
    private String mentorCareer;
    private String mentorExCareer;
    private String mentorStrongTitle1;
    private String mentorStrongContent1;
    private String mentorStrongTitle2;
    private String mentorStrongContent2;
    private String mentorStrongTitle3;
    private String mentorStrongContent3;
    private String mentorTitle;
    private String mentorTitleSub;
    private String mentorTextTitle;
    private String mentorTextContent;
    private Member memberId;

    public MentorBoard toEntity(){
        return MentorBoard.builder()
                .mentorCareer(mentorCareer)
                .mentorExCareer(mentorExCareer)
                .mentorStrongContent1(mentorStrongContent1)
                .mentorStrongContent2(mentorStrongContent2)
                .mentorStrongContent3(mentorStrongContent3)
                .mentorStrongTitle1(mentorStrongTitle1)
                .mentorStrongTitle2(mentorStrongTitle2)
                .mentorStrongTitle3(mentorStrongTitle3)
                .mentorTextContent(mentorTextContent)
                .mentorTextTitle(mentorTextTitle)
                .mentorTitle(mentorTitle)
                .mentorTitleSub(mentorTitleSub)
                .build();
    }
}