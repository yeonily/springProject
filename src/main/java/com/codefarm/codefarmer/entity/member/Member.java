package com.codefarm.codefarmer.entity.member;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.type.MemberType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Period {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @NotNull
    private String memberName;
    @NotNull
    @Column(unique = true)
    private String memberNickname;
    @NotNull
    private String memberPhone;
    @NotNull
    private String memberLocation;
    @NotNull
    private String memberBirth;
    @NotNull
    private String memberEmail;
    @NotNull
    private String memberOauthId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public void updateNick(MemberDTO memberDTO){
        this.memberNickname = memberDTO.getMemberNickname();
    }

    public void update(MemberDTO memberDTO) {
        this.memberPhone = memberDTO.getMemberPhone();
        this.memberLocation = memberDTO.getMemberLocation();
    }


    @Builder
    public Member(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, String memberOauthId, MemberType memberType) {
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberOauthId = memberOauthId;
        this.memberType = memberType;
    }
}
