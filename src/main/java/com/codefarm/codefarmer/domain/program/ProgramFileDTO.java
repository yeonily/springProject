package com.codefarm.codefarmer.domain.program;

import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
    private Long programId;

    public ProgramFile toEntity(){
        return ProgramFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }

    @QueryProjection
    public ProgramFileDTO(Long fileId, String fileName, String fileUploadPath, String fileUuid, Long fileSize, boolean fileImageCheck) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }

}