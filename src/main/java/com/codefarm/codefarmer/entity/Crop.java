package com.codefarm.codefarmer.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CROP")
@Getter @Setter @ToString
@NoArgsConstructor
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

    public void update(Crop crop){
        this.cropKeyword = crop.getCropKeyword();
        this.cropTitle = crop.getCropTitle();
        this.cropImage = crop.getCropImage();
        this.cropContent = crop.getCropContent();
    }

    @Builder
    public Crop(String cropKeyword, String cropTitle, String cropImage, String cropContent) {
        this.cropKeyword = cropKeyword;
        this.cropTitle = cropTitle;
        this.cropImage = cropImage;
        this.cropContent = cropContent;
    }
}
