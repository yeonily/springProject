package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private String mentorCrop;
    private String mentorYear;
    private String memberLocation;
    private String memberNickName;
    private Long memberId;
    private Long mentorId;
    private List<MentorFileDTO> files;

    @QueryProjection
    public MentorBoardDTO(Long mentorBoardId, String mentorCareer, String mentorExCareer, String mentorStrongTitle1, String mentorStrongContent1, String mentorStrongTitle2, String mentorStrongContent2, String mentorStrongTitle3, String mentorStrongContent3, String mentorTitle, String mentorTitleSub, String mentorTextTitle, String mentorTextContent, String mentorCrop) {
        this.mentorBoardId = mentorBoardId;
        this.mentorCareer = mentorCareer;
        this.mentorExCareer = mentorExCareer;
        this.mentorStrongTitle1 = mentorStrongTitle1;
        this.mentorStrongContent1 = mentorStrongContent1;
        this.mentorStrongTitle2 = mentorStrongTitle2;
        this.mentorStrongContent2 = mentorStrongContent2;
        this.mentorStrongTitle3 = mentorStrongTitle3;
        this.mentorStrongContent3 = mentorStrongContent3;
        this.mentorTitle = mentorTitle;
        this.mentorTitleSub = mentorTitleSub;
        this.mentorTextTitle = mentorTextTitle;
        this.mentorTextContent = mentorTextContent;
        this.mentorCrop = mentorCrop;
    }

    @QueryProjection
    public MentorBoardDTO(String mentorCrop, String mentorYear, String memberLocation, String memberNickName) {
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
        this.memberLocation = memberLocation;
        this.memberNickName = memberNickName;
    }



    @QueryProjection
    public MentorBoardDTO(Long mentorBoardId, String mentorTitle, String memberNickName) {
        this.mentorBoardId = mentorBoardId;
        this.mentorTitle = mentorTitle;
        this.memberNickName = memberNickName;
    }


    @QueryProjection
    public MentorBoardDTO(Long mentorBoardId, String mentorCareer, String mentorExCareer, String mentorStrongTitle1, String mentorStrongContent1, String mentorStrongTitle2, String mentorStrongContent2, String mentorStrongTitle3, String mentorStrongContent3, String mentorTitle, String mentorTitleSub, String mentorTextTitle, String mentorTextContent, Long memberId, Long mentorId) {
        this.mentorBoardId = mentorBoardId;
        this.mentorCareer = mentorCareer;
        this.mentorExCareer = mentorExCareer;
        this.mentorStrongTitle1 = mentorStrongTitle1;
        this.mentorStrongContent1 = mentorStrongContent1;
        this.mentorStrongTitle2 = mentorStrongTitle2;
        this.mentorStrongContent2 = mentorStrongContent2;
        this.mentorStrongTitle3 = mentorStrongTitle3;
        this.mentorStrongContent3 = mentorStrongContent3;
        this.mentorTitle = mentorTitle;
        this.mentorTitleSub = mentorTitleSub;
        this.mentorTextTitle = mentorTextTitle;
        this.mentorTextContent = mentorTextContent;
        this.memberId = memberId;
        this.mentorId = mentorId;
    }

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