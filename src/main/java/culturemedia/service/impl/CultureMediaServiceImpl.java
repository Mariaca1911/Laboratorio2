package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewRepository;
import culturemedia.service.CultureMediaServices;

import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaServices {
    private VideoRepository videoRepository;
    private ViewRepository viewRepository;

    public CultureMediaServiceImpl(VideoRepository videoRepository, ViewRepository viewRepository) {
        this.videoRepository = videoRepository;
        this.viewRepository = viewRepository;
    }

    @Override
    public Video add(Video video){
        Video videoAdd = videoRepository.save(video);
        return videoAdd;
    }

    @Override
    public View add(View view) {
        View viewAdd = viewRepository.add(view);
        return viewAdd;
    }

    @Override
    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if(videos.isEmpty()){
            throw new VideoNotFoundException();
        }
        else{
            return videos;
        }
    }
    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(title);
        if(videos.isEmpty()){
            throw new VideoNotFoundException();
        }
        else{
            return videos;
        }
    }

    @Override
    public List<Video> find(double fromDuration, double toDuration) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(fromDuration, toDuration);
        if(videos.isEmpty()){
            throw new VideoNotFoundException();
        }
        else{
            return videos;
        }
    }


}
