package com.codefarm.codefarmer.domain.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import com.codefarm.codefarmer.entity.notice.NoticeFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class NoticeFileDTO {
    private Long fileId;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;
    private Long noticeId;

    public NoticeFile toEntity(){
        return NoticeFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }

    @QueryProjection
    public NoticeFileDTO(Long fileId, String fileName, String fileUploadPath, String fileUuid, Long fileSize, boolean fileImageCheck) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}