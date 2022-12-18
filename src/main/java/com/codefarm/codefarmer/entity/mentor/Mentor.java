package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_MENTOR")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentor extends Period {
    @Id @GeneratedValue
    private Long mentorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @NotNull
    private String mentorCrop;
    @NotNull
    private String mentorYear;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Program> programs;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "mentor", cascade = CascadeType.REMOVE)
    private List<MentorMentee> mentorMentees;

    public void update(MentorDTO mentorDTO){
        this.mentorCrop = mentorDTO.getMentorCrop();
        this.mentorYear = mentorDTO.getMentorYear();
    }

    public void changeMember(Member member){
        this.member = member;
    }


    @Builder
    public Mentor(String mentorCrop, String mentorYear) {
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
    }
}