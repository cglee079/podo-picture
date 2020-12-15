package com.podo.picture.file;

import com.podo.picture.model.Picture;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class PictureStore {

    private final List<File> pictures;

    public PictureStore() {
        this.pictures = new FileReader().readFilesInFolder("//192.168.50.2//podo-picture");
    }

    public Picture next(Long id) {
        if(id == null){
            return new Picture(0L, pictures.get(0));
        }

        Long nextId = id + 1;

        if (nextId >= pictures.size()) {
            nextId = 0L;
        }

        return new Picture(nextId, pictures.get(nextId.intValue()));
    }

}
