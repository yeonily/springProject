package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FARMER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Farmer extends Member{
    @Enumerated(EnumType.STRING)
    private FarmerType farmerType;

    @Builder
    public Farmer(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth, FarmerType farmerType) {
        super(memberName, memberNickname, memberPhone, memberLocation, memberBirth, memberEmail, memberOauth);
        this.farmerType = farmerType;
    }
}
