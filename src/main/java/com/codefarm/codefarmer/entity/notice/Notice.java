package com.codefarm.codefarmer.entity.notice;

<<<<<<< HEAD:src/main/java/com/codefarm/codefarmer/entity/notice/Notice.java
import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.entity.period.Period;
=======
>>>>>>> origin/master:src/main/java/com/codefarm/codefarmer/entity/Notice.java
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_NOTICE")
<<<<<<< HEAD:src/main/java/com/codefarm/codefarmer/entity/notice/Notice.java
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
=======
@Getter @Setter @ToString
@NoArgsConstructor
public class Notice extends Period{
>>>>>>> origin/master:src/main/java/com/codefarm/codefarmer/entity/Notice.java
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

    public void update(Notice notice){
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeViewCount = notice.getNoticeViewCount();
        this.noticeFiles = notice.getNoticeFiles();
    }

    @Builder
    public Notice(String noticeTitle, String noticeContent, int noticeViewCount, List<NoticeFile> noticeFiles) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeViewCount = noticeViewCount;
        this.noticeFiles = noticeFiles;
    }
}
