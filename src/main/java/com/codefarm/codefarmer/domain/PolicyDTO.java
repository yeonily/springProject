package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Notice;
import com.codefarm.codefarmer.entity.Policy;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class PolicyDTO {
    private Long policyId;
    private String policyKeyword;
    private String policyTitle;
    private String policyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public Policy toEntity(){
        return Policy.builder()
                .policyContent(policyContent)
                .policyKeyword(policyKeyword)
                .policyTitle(policyTitle)
                .build();
    }
}