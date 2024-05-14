import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import culturemedia.service.CultureMediaServices;
import culturemedia.service.impl.CultureMediaServiceImpl;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.*;
import culturemedia.repository.impl.*;

class CultureMediaServiceImplTest {
    private CultureMediaServices cultureMediaService;

    private Video exampleVideo1 = new Video("01", "Title 1", "Hello, this is a new video to.....", 4.5);
    private Video exampleVideo2 = new Video("02", "Title 2", "Hello, this is a new video to.....", 5.5);
    private Video exampleVideo3 = new Video("03", "Title 3", "Hello, this is a new video to.....", 4.4);
    private Video exampleVideo4 = new Video("04", "Title 4", "Hello, this is a new video to.....", 3.5);
    private Video exampleVideo5 = new Video("05", "Title 5", "Hello, this is a new video to.....", 5.7);
    private Video exampleVideo6 = new Video("06", "Title 6", "Hello, this is a new video to.....", 5.1);

    @BeforeEach
    void init() {
        VideoRepository videoRepository = new VideoRepositoryImpl();
        ViewRepository viewsRepository = new ViewRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.findAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.findAllVideos();
            assertEquals(6, Videos.size());
        } catch (VideoNotFoundException e) {
            assert(false);
        }
    }
    @Test
    void when_find_ForTitle_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find("anything");
        } );
    }

    @Test
    void when_Find_ByDuration_an_VideoNotFoundException_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find(0.0,0.9);
        });

    }

    @Test
    void when_findByTitle_should_be_returned_successfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find("Hilmer");
        });
    }

    @Test
    void when_findByDuration_should_be_returned_succesfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try{
            List<Video> video = cultureMediaService.find(0, 5.5);
            assertEquals(5, video.size());
        }
        catch (VideoNotFoundException e){
            assert(false);
        }
    }
    }