package com.codefarm.codefarmer.entity.program;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

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
}
