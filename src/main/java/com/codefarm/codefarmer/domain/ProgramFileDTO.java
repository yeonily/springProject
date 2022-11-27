package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.*;
import com.codefarm.codefarmer.type.ProgramLevel;
import com.codefarm.codefarmer.type.ProgramType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ProgramFileDTO {
    private Long fileId;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;
    private Program program;

    public ProgramFile toEntity(){
        return ProgramFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }

}