package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class MentorDTO {
    private Long mentorId;
    private Long memberId;
    private Member farmerType;
    private String mentorCrop;
    private String mentorYear;

    public Mentor toEntity(){
        return Mentor.builder()
                .mentorCrop(mentorCrop)
                .mentorYear(mentorYear)
                .build();
    }
}