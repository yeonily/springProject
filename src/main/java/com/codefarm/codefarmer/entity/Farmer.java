package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FARMER")
@Getter
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@DiscriminatorValue("FARMER")
public class Farmer extends Member{
    @NotNull
    @Enumerated(EnumType.STRING)
    private FarmerType farmerType;

    public void update(FarmerDTO farmerDTO){
        super.update(farmerDTO.getMemberNickname(), farmerDTO.getMemberPhone(), farmerDTO.getMemberLocation(), farmerDTO.getMemberEmail());
        this.farmerType = farmerType;
    }

    @Builder
    public Farmer(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth, FarmerType farmerType) {
        super(memberName, memberNickname, memberPhone, memberLocation, memberBirth, memberEmail, memberOauth);
        this.farmerType = farmerType;
    }
}
