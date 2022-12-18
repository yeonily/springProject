package com.codefarm.codefarmer.entity.notice;

import com.codefarm.codefarmer.domain.notice.NoticeFileDTO;
import com.codefarm.codefarmer.entity.period.Period;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NoticeFile extends Period{
    @Id @GeneratedValue
    private Long fileId;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Notice notice;

    public void changeNotice(Notice notice){
        this.notice = notice;
    }

    public void update(NoticeFileDTO NoticeFileDTO){
        this.fileName = NoticeFileDTO.getFileName();
        this.fileUploadPath = NoticeFileDTO.getFileUploadPath();
        this.fileUuid = NoticeFileDTO.getFileUuid();
        this.fileSize = NoticeFileDTO.getFileSize();
    }

    @Builder
    public NoticeFile(String fileName, String fileUploadPath, String fileUuid, Long fileSize, boolean fileImageCheck) {
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}
