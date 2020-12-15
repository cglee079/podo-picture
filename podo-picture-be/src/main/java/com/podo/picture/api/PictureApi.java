package com.podo.picture.api;

import com.podo.picture.model.PictureDto;
import com.podo.picture.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PictureApi {

    private final PictureService pictureService;

    @GetMapping("/api/picture/next")
    public PictureDto nextPicture(@RequestParam(required = false) Long id){
        return pictureService.next(id);
    }

}

