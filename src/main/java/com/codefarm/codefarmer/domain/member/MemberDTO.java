package com.codefarm.codefarmer.domain.member;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.MemberType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class MemberDTO {
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String memberPhone;
    private String memberLocation;
    private String memberBirth;
    private String memberEmail;
    private String memberOauthId;
    private MemberType memberType;


   public Member toEntity(){
       return Member.builder()
               .memberType(memberType)
               .memberBirth(memberBirth)
               .memberEmail(memberEmail)
               .memberLocation(memberLocation)
               .memberName(memberName)
               .memberNickname(memberNickname)
               .memberOauthId(memberOauthId)
               .memberPhone(memberPhone)
               .build();
   }

    @QueryProjection
    public MemberDTO(Long memberId, String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, String memberOauthId, MemberType memberType) {
        this.memberId = memberId;
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
