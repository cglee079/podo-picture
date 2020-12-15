package com.podo.picture.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PictureDto {
    private Long id;
    private String url;
    private PictureType pictureType;
    private Integer duration;
}
