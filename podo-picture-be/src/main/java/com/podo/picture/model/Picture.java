package com.podo.picture.model;

import com.coremedia.iso.IsoFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

@Getter
@AllArgsConstructor
public class Picture {
    private Long id;
    private String filename;

    @Getter(AccessLevel.PRIVATE)
    private Integer duration;

    private PictureType pictureType;

    public Picture(Long id, File file) {
        this.id = id;
        this.filename = file.getName();
        this.pictureType = extractGetPictureType(file.getName());
        this.duration = extractGetDuration(file, pictureType);
    }

    private Integer extractGetDuration(File file, PictureType pictureType) {
        if (pictureType == PictureType.IMAGE) {
            return 0;
        }
        IsoFile isoFile = null;
        try {
            isoFile = new IsoFile(file.getAbsolutePath());

            return Double.valueOf((double)
                    isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                    isoFile.getMovieBox().getMovieHeaderBox().getTimescale()).intValue();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private PictureType extractGetPictureType(String filename) {
        String mimeType = URLConnection.guessContentTypeFromName(filename);

        if (mimeType.startsWith("video")) {
            return PictureType.VIDEO;
        }

        return PictureType.IMAGE;
    }

    public Integer getDurationSecond(Integer min, Integer max) {
        if (duration < min) {
            return min;
        }

        if (duration > max) {
            return max;
        }

        return duration;
    }

}
