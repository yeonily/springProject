package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.BoardFile;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MentorBoard;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class BoardFileDTO {
    private Long fileId;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;
    private Board board;

    public BoardFile toEntity(){
        return BoardFile.builder()
                .fileName(fileName)
                .fileUploadPath(fileUploadPath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }
}
