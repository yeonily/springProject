package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.CropDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CROP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Crop extends Period{
    @Id @GeneratedValue
    private Long cropId;
    @Column(nullable = false)
    private String cropKeyword;
    @Column(nullable = false)
    private String cropTitle;
    @Column(nullable = false)
    private String cropImage;
    @Column(nullable = false)
    private String cropContent;

    public void update(CropDTO cropDTO){
        this.cropKeyword = cropKeyword;
        this.cropTitle = cropTitle;
        this.cropImage = cropImage;
        this.cropContent = cropContent;
    }

    @Builder
    public Crop(String cropKeyword, String cropTitle, String cropImage, String cropContent) {
        this.cropKeyword = cropKeyword;
        this.cropTitle = cropTitle;
        this.cropImage = cropImage;
        this.cropContent = cropContent;
    }
}
