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
    private int amount;
    private String searchText;
    private String keyword;


    public void createCriteria(){
        createCriteria(1, 10);
    }

    public void createCriteria(int page, int amount){
        this.page = page;
        this.amount = amount;
    }

    public String getQueryString(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", this.page)
                .queryParam("amount", this.amount);
//                .queryParam("searchText", this.searchText)
//                .queryParam("keyword", this.keyword);
        log.info("Criteria.. → " + builder.toUriString());
        return builder.toUriString();
    }

    public String[] getKeyword() {
        return keyword != null ? keyword.split("") : new String[] {};
    }

}
