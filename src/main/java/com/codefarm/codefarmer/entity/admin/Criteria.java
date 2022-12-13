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

    public void createCriteria(int page){
        this.page = page;
    }

    public void createCriteria(int page, String searchText, String keyword){
        this.page = page;
        this.searchText = searchText;
        this.keyword = keyword;
    }

    public String getQueryString(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", this.page)
                .queryParam("searchText", this.searchText)
                .queryParam("keyword", this.keyword);
        return builder.toUriString();
    }

    public String getNoticeQueryString(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", this.page);
        return builder.toUriString();
    }

}
