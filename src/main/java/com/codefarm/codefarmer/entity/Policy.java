package com.codefarm.codefarmer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POLICY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Policy extends Period{
    @Id @GeneratedValue
    private Long policyId;
    @Column(nullable = false)
    private String policyKeyword;
    @Column(nullable = false)
    private String policyTitle;
    @Column(nullable = false)
    private String policyContent;

    @Builder
    public Policy(String policyKeyword, String policyTitle, String policyContent) {
        this.policyKeyword = policyKeyword;
        this.policyTitle = policyTitle;
        this.policyContent = policyContent;
    }
}
