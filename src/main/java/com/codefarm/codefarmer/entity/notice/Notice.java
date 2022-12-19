package com.codefarm.codefarmer.entity.notice;

import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TBL_NOTICE")
@Getter @Setter @ToString
@NoArgsConstructor
public class Notice extends Period{
    @Id @GeneratedValue
    private Long noticeId;
    @NotNull
    private String noticeTitle;
    @NotNull @Column(length = 1000)
    private String noticeContent;
    @ColumnDefault("0")
    private int noticeViewCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice", cascade = CascadeType.ALL)
    private List<NoticeFile> noticeFiles;

    public void changeFiles(List<NoticeFileDTO> files){
        List<NoticeFile> fileEntities = files.stream().map(file -> file.toEntity()).collect(Collectors.toList());
        fileEntities.stream().forEach(file -> file.changeNotice(this));
        this.noticeFiles = fileEntities;
    }

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
