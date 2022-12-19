package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POLICY")
@Getter @Setter @ToString
@Slf4j
@NoArgsConstructor
public class Policy extends Period{
    @Id @GeneratedValue
    private Long policyId;
    @NotNull
    private String policyKeyword;
    @NotNull
    private String policyTitle;
    @NotNull @Column(length = 1000)
    private String policyContent;

    @Builder
    public Policy(String policyKeyword, String policyTitle, String policyContent) {
        this.policyKeyword = policyKeyword;
        this.policyTitle = policyTitle;
        this.policyContent = policyContent;
    }
}
