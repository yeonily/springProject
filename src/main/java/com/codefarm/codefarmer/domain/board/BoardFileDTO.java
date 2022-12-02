package com.codefarm.codefarmer.domain.board;

import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.BoardFile;
import lombok.*;
import org.springframework.stereotype.Component;

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
