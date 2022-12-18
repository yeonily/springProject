package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.entity.program.Program;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorFile extends Period {
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
    @JoinColumn(name = "MENTOR_BOARD_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MentorBoard mentorBoard;

    public void changeMentor(MentorBoard mentorBoard){
        this.mentorBoard = mentorBoard;
    }

    @Builder
    public MentorFile(@NotNull String fileName, @NotNull String fileUploadPath, @NotNull String fileUuid, @NotNull Long fileSize, @NotNull boolean fileImageCheck) {
        this.fileName = fileName;
        this.fileUploadPath = fileUploadPath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}
