package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.MentorFile;
import com.codefarm.codefarmer.entity.Notice;
import com.codefarm.codefarmer.entity.NoticeFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private Notice notice;

    public NoticeFile toEntity(){
        return NoticeFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }
}