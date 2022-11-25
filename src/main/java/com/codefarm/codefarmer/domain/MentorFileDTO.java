package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MentorBoard;
import com.codefarm.codefarmer.entity.MentorFile;
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
    private String fileRealname;
    private MentorBoard mentorBoard;

    public MentorFile toEntity(){
        return MentorFile.builder()
                .fileRealname(fileRealname)
                .build();
    }
}