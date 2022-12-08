package com.codefarm.codefarmer.entity.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    private int page;
    private String searchText;
    private String keyword;
}
