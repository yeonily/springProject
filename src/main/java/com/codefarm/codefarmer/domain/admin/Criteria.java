package com.codefarm.codefarmer.domain.admin;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
public class Criteria {
    private int page;
    private int amount;
    private String type;
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
        return builder.toUriString();
    }

    public String[] getTypes(){
        return type != null ? type.split("") : new String[] {};
    }
}
