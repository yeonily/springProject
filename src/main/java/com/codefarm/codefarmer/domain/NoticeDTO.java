package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.Notice;
import com.codefarm.codefarmer.entity.NoticeFile;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private int noticeViewCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent, int noticeViewCount, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeViewCount = noticeViewCount;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public Notice toEntity(){
        return Notice.builder()
                .noticeContent(noticeContent)
                .noticeTitle(noticeTitle)
                .noticeViewCount(noticeViewCount)
                .build();
    }
}