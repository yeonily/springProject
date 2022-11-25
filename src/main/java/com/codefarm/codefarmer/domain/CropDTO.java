package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Crop;
import com.codefarm.codefarmer.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
@NoArgsConstructor
@Data
public class CropDTO {
    private Long cropId;
    private String cropKeyword;
    private String cropTitle;
    private String cropImage;
    private String cropContent;

    public Crop toEntity(){
        return Crop.builder()
                .cropContent(cropContent)
                .cropImage(cropImage)
                .cropKeyword(cropKeyword)
                .cropTitle(cropTitle)
                .build();
    }
}
