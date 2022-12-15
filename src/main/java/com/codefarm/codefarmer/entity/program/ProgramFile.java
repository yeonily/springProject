package com.codefarm.codefarmer.entity.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PROGRAM_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramFile extends Period {
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
    @JoinColumn(name = "PROGRAM_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    public void changeProgram(Program program){
        this.program = program;
    }

    @Builder
    public ProgramFile(String fileName, String fileUploadPath, String fileUuid, Long fileSize, boolean fileImageCheck) {
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }

    public void update(ProgramFileDTO programFileDTO){
//        this.programCrop = programDTO.getProgramCrop();
        this.fileId = programFileDTO.getFileId();
        this.fileName = programFileDTO.getFileName();
        this.fileUploadPath = programFileDTO.getFileUploadPath();
        this.fileUuid = programFileDTO.getFileUuid();
        this.fileSize = programFileDTO.getFileSize();
        this.fileImageCheck = programFileDTO.isFileImageCheck();
    }
}
