package com.codefarm.codefarmer.domain.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PageDTO {
    //    페이지 단위 수
    private int pageCount;
    //    현재 페이지를 기준으로 시작 페이지
    private int startPage;
    //    현재 페이지를 기준으로 마지막 페이지
    private int endPage;
    //    가장 마지막 페이지
    private int realEnd;
    private boolean prev, next;
    //    전체 게시글 개수
    private int total;
    //    화면에서 받아온 page, amount를 필드로 구성한 객체
    private Criteria criteria;

    public void createPageDTO(Criteria criteria, int total){
        createPageDTO(criteria, total, 10);
    }

    public void createPageDTO(Criteria criteria, int total, int pageCount){
        this.criteria = criteria;
        this.total = total;
        this.pageCount = pageCount;
//        현재 페이지를 기준으로 페이지 단위에 맞춰서 마지막 페이지 계산
        this.endPage = (int)(Math.ceil(criteria.getPage() / (double)pageCount)) * pageCount;
        this.startPage = endPage - pageCount + 1;
//        게시글 전체 개수를 통해 가장 마지막 페이지 계산
        this.realEnd = (int)(Math.ceil((double)total / criteria.getAmount()));
//        만약 가장 마지막 페이지보다 마지막 페이지가 더 클 경우(endPage는 배수로 증가하기 떄문)
        if(realEnd < endPage){
//            게시글이 한 개도 없다면, realEnd는 0이 되고, endPage도 0이 된다.
//            따라서 realEnd가 0이라면 endPage를 1로 변경해주어야 한다.
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
