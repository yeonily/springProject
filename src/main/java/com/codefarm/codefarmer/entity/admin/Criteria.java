package com.codefarm.codefarmer.entity.admin;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
@Slf4j
public class Criteria {
    private int page;
    private String searchText;
    private String keyword;

    public String getQueryString(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", this.page);
        log.info("크리테리아... → " + builder.toUriString());
        return builder.toUriString();
    }

    public String[] getKeyword() {
        return keyword != null ? keyword.split("") : new String[] {};
    }

}
