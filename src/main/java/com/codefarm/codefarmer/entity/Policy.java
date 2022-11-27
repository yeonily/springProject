package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.PolicyDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POLICY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Policy extends Period{
    @Id @GeneratedValue
    private Long policyId;
    @NotNull
    private String policyKeyword;
    @NotNull
    private String policyTitle;
    @NotNull
    private String policyContent;

    public void update(PolicyDTO policyDTO){
        this.policyKeyword = policyKeyword;
        this.policyTitle = policyTitle;
        this.policyContent = policyContent;
    }

    @Builder
    public Policy(String policyKeyword, String policyTitle, String policyContent) {
        this.policyKeyword = policyKeyword;
        this.policyTitle = policyTitle;
        this.policyContent = policyContent;
    }
}
