package com.podo.picture.service;

import com.podo.picture.model.Picture;
import com.podo.picture.model.PictureDto;
import com.podo.picture.file.PictureStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public
class PictureService {

    private final String STATIC_URL = "http://static.podo-picture.com/";

    private final PictureStore pictureStore;

    public PictureDto next(Long id) {
        final Picture picture = pictureStore.next(id);
        final String url = STATIC_URL + picture.getFilename();

        return new PictureDto(picture.getId(), url, picture.getPictureType(), picture.getDurationSecond(10,30));
    }

}
