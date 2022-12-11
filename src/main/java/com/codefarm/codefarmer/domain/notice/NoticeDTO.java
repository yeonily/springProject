package com.codefarm.codefarmer.domain.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
    private List<NoticeFileDTO> noticeFiles;

    public Notice toEntity(){
        return Notice.builder()
                .noticeContent(noticeContent)
                .noticeTitle(noticeTitle)
                .noticeViewCount(noticeViewCount)
                .build();
    }

    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent, int noticeViewCount, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeViewCount = noticeViewCount;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }
}