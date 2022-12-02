package com.codefarm.codefarmer.domain.member;

import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.UserType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class UserDTO {
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String memberPhone;
    private String memberLocation;
    private String memberBirth;
    private String memberEmail;
    private Oauth memberOauth;
    private UserType userType;


   public User toEntity(){
       return User.builder()
               .userType(userType)
               .memberBirth(memberBirth)
               .memberEmail(memberEmail)
               .memberLocation(memberLocation)
               .memberName(memberName)
               .memberNickname(memberNickname)
               .memberOauth(memberOauth)
               .memberPhone(memberPhone)
               .build();
   }

    @QueryProjection
    public UserDTO(Long memberId, String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberOauth = memberOauth;
    }
}
