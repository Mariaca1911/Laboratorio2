import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;
import java.util.List;

import culturemedia.service.CultureMediaServices;
import culturemedia.service.impl.CultureMediaServiceImpl;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.*;
import culturemedia.repository.impl.*;
import org.mockito.Mockito;

class CultureMediaServiceImplTest {
    private CultureMediaServices cultureMediaService;
    private VideoRepository videoRepository = Mockito.mock();

    private Video exampleVideo1 = new Video("01", "Title 1", "Hello, this is a new video to.....", 4.5);
    private Video exampleVideo2 = new Video("02", "Title 2", "Hello, this is a new video to.....", 5.5);
    private Video exampleVideo3 = new Video("03", "Title 3", "Hello, this is a new video to.....", 4.4);
    private Video exampleVideo4 = new Video("04", "Title 4", "Hello, this is a new video to.....", 3.5);
    private Video exampleVideo5 = new Video("05", "Title 5", "Hello, this is a new video to.....", 5.7);
    private Video exampleVideo6 = new Video("06", "Title 6", "Hello, this is a new video to.....", 5.1);

    @BeforeEach
    void init() {
        ViewRepository viewsRepository = new ViewRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        mockVideoRepositoryFindAll(Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.findAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {

        mockVideoRepositoryFindAll(
                List.of(
                        exampleVideo1,
                        exampleVideo2,
                        exampleVideo3,
                        exampleVideo4,
                        exampleVideo5,
                        exampleVideo6)
        );
        List<Video> videos = cultureMediaService.findAllVideos();
        assertEquals(6, videos.size());
    }

    @Test
    void when_find_forTitle_an_VideoNotFoundExeption_should_be_thrown_successfully() {
        mockVideoRepositoryFind(null, Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find("any Thing");
        });
    }

    @Test
    void when_find_forDuration_an_VideoNotFoundExeption_should_be_thrown_successfully() {
        mockVideoRepositoryFind(null, null, Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find(0.0, 0.5);
        });
    }

    @Test
    void when_find_forTitle_should_be_returned_succesfully() throws VideoNotFoundException{

        mockVideoRepositoryFind("Hilmer", List.of(exampleVideo6));

        List<Video> videos = cultureMediaService.find("Hilmer");
        assertEquals(1, videos.size());
        assertEquals(exampleVideo6, videos.get(0));
    }

    @Test
    void when_find_forDuration_should_be_returned_succesfully() throws VideoNotFoundException{

        mockVideoRepositoryFind(0.0, 5.5, List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo6));
        List<Video> videos = cultureMediaService.find(0.0, 5.5);
        assertEquals(5, videos.size());
    }

    private void mockVideoRepositoryFindAll(List <Video> videos){
        doReturn(videos).when(videoRepository).findAll();
    }

    private void mockVideoRepositoryFind(String title, List<Video> videos){
        doReturn(videos).when(videoRepository).find(eq(title));
    }

    private void mockVideoRepositoryFind(Double fromDuration, Double toDuration, List<Video> videos){
        doReturn(videos).when(videoRepository).find(eq(fromDuration), eq(toDuration));
    }
}


