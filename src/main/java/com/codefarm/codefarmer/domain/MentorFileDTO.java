package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.*;
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
public class MentorFileDTO {

    private Long fileId;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;
    private Mentor mentor;

    public MentorFile toEntity(){
        return MentorFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }
}