package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Data
public class MentorDTO {
    private Long mentorId;
    private Long memberId;
    private Member farmerType;
    private String mentorCrop;
    private String mentorYear;
    private String memberLocation;
    private String memberName;
    private String memberNickname;
    private int programCount;
    private int menteeCount;

    private List<ProgramDTO> programs;
    private List<MentorMenteeDTO> mentorMentees;

    public Mentor toEntity(){
        return Mentor.builder()
                .mentorCrop(mentorCrop)
                .mentorYear(mentorYear)
                .build();
    }

    @QueryProjection
    public MentorDTO(Long mentorId, Long memberId, String memberName, String memberNickname , String mentorYear, String memberLocation, int programCount, int menteeCount) {
        this.mentorId = mentorId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.mentorYear = mentorYear;
        this.memberLocation = memberLocation;
        this.programCount = programCount;
        this.menteeCount = menteeCount;
    }

    @QueryProjection
    public MentorDTO(Long mentorId, Long memberId, Member farmerType, String mentorCrop, String mentorYear) {
        this.mentorId = mentorId;
        this.memberId = memberId;
        this.farmerType = farmerType;
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
    }

    @QueryProjection
    public MentorDTO(String mentorCrop, String mentorYear, String memberLocation, String memberNickname) {
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
        this.memberLocation = memberLocation;
        this.memberNickname = memberNickname;
    }


}