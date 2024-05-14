package culturemedia.controllers;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewRepositoryImpl;
import culturemedia.service.CultureMediaServices;
import culturemedia.service.impl.CultureMediaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Collections;
import java.util.List;

public class CultureMediaController {
    private final CultureMediaServices cultureMediaService;


    public CultureMediaController() {
        this.cultureMediaService = new CultureMediaServiceImpl(new VideoRepositoryImpl(), new ViewRepositoryImpl());
    }


    @GetMapping("/videos")
    public ResponseEntity<List<Video>> findAllVideos() throws VideoNotFoundException {
        try {
            return ResponseEntity.status( HttpStatus.OK ).body( cultureMediaService.findAllVideos() );
        } catch (VideoNotFoundException e) {
            return ResponseEntity.status ( HttpStatus.BAD_REQUEST)
                    .header( "Error_name", e.getMessage() )
                    .body(Collections.emptyList());
        }
    }

    @PostMapping("/videos")
    public ResponseEntity<Video> add(@RequestBody Video video) {
        return ResponseEntity.status( HttpStatus.OK ).body(cultureMediaService.add(video));
    }
}
