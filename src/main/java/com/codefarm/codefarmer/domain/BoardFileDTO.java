package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.BoardFile;
import com.codefarm.codefarmer.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    private String fileRealname;
    private Board board;

    public BoardFile toEntity(){
        return BoardFile.builder()
                .fileRealname(fileRealname)
                .build();
    }
}
