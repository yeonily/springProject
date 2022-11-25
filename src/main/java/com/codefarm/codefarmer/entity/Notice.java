package com.codefarm.codefarmer.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_NOTICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Notice extends Period{
    @Id @GeneratedValue
    private Long noticeId;
    @Column(nullable = false)
    private String noticeTitle;
    @Column(nullable = false)
    private String noticeContent;
    @ColumnDefault("0")
    private int noticeViewCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice")
    private List<NoticeFile> noticeFiles;

    @Builder
    public Notice(String noticeTitle, String noticeContent, int noticeViewCount) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeViewCount = noticeViewCount;
    }
}
