package com.codefarm.codefarmer.domain.mentor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class MentorCriteria {
    private int page;
    private String keyword;
}
