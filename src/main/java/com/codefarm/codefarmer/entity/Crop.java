package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.CropDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CROP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crop extends Period{
    @Id @GeneratedValue
    private Long cropId;
    @NotNull
    private String cropKeyword;
    @NotNull
    private String cropTitle;
    @NotNull
    private String cropImage;
    @NotNull
    private String cropContent;

    public void update(CropDTO cropDTO){
        this.cropKeyword = cropDTO.getCropKeyword();
        this.cropTitle = cropDTO.getCropTitle();
        this.cropImage = cropDTO.getCropImage();
        this.cropContent = cropDTO.getCropContent();
    }

    @Builder
    public Crop(String cropKeyword, String cropTitle, String cropImage, String cropContent) {
        this.cropKeyword = cropKeyword;
        this.cropTitle = cropTitle;
        this.cropImage = cropImage;
        this.cropContent = cropContent;
    }
}
