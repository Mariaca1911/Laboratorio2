package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface CultureMediaServices {
    List<Video> findAllVideos() throws VideoNotFoundException;
    Video add(Video video);
    View add(View view);
    List<Video> find(String title) throws VideoNotFoundException;
    List<Video> find(double fromDuration, double toDuration) throws VideoNotFoundException;

}

