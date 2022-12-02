package com.codefarm.codefarmer.entity.notice;

import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE_FILE")
<<<<<<< HEAD:src/main/java/com/codefarm/codefarmer/entity/notice/NoticeFile.java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeFile extends Period {
=======
@Getter @Setter
@NoArgsConstructor
public class NoticeFile extends Period{
>>>>>>> origin/master:src/main/java/com/codefarm/codefarmer/entity/NoticeFile.java
    @Id @GeneratedValue
    private Long fileId;
    @NotNull
    private String fileName;
    @NotNull
    private String fileUploadPath;
    @NotNull
    private String fileUuid;
    @NotNull
    private Long fileSize;
    @NotNull
    private boolean fileImageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_ID")
    private Notice notice;

    @Builder
    public NoticeFile(String fileName, String fileUploadPath, String fileUuid, Long fileSize, boolean fileImageCheck) {
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}
