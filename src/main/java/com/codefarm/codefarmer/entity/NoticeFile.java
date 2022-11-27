package com.codefarm.codefarmer.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeFile extends Period{
    @Id @GeneratedValue
    private Long fileId;
    @NonNull
    private String fileName;
    @NonNull
    private String fileUploadPath;
    @NonNull
    private String fileUuid;
    @NonNull
    private Long fileSize;
    @NonNull
    private boolean fileImageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_ID")
    private Notice notice;

    @Builder
    public NoticeFile(@NonNull String fileName, @NonNull String fileUploadPath, @NonNull String fileUuid, @NonNull Long fileSize, @NonNull boolean fileImageCheck) {
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}
