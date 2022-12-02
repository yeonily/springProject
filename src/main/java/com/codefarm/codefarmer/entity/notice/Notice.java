package com.codefarm.codefarmer.entity.notice;

import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_NOTICE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue
    private Long noticeId;
    @NotNull
    private String noticeTitle;
    @NotNull
    private String noticeContent;
    @ColumnDefault("0")
    private int noticeViewCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice")
    private List<NoticeFile> noticeFiles;

    public void update(NoticeDTO noticeDTO){
        this.noticeTitle = noticeDTO.getNoticeTitle();
        this.noticeContent = noticeDTO.getNoticeContent();
        this.noticeViewCount = noticeDTO.getNoticeViewCount();
    }

    @Builder
    public Notice(String noticeTitle, String noticeContent, int noticeViewCount) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeViewCount = noticeViewCount;
    }
}
