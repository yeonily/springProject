package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.Program;
import com.codefarm.codefarmer.entity.ProgramFile;
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
    private String fileRealname;

    private Program program;

    public ProgramFile toEntity(){
        return ProgramFile.builder()
                .fileRealname(fileRealname)
                .build();
    }

}