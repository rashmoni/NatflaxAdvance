package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Episode;
import com.novare.natflax.NatflaxAdvance.Entity.Series;
import com.novare.natflax.NatflaxAdvance.Exceptions.ResourceNotFoundException;
import com.novare.natflax.NatflaxAdvance.Payloads.EpisodeDto;
import com.novare.natflax.NatflaxAdvance.Repositories.EpisodeRepo;
import com.novare.natflax.NatflaxAdvance.Repositories.SeriesRepo;
import com.novare.natflax.NatflaxAdvance.Services.EpisodeService;
import com.novare.natflax.NatflaxAdvance.Services.FileSystemStorageService;
import com.novare.natflax.NatflaxAdvance.Services.IStorageService;
import com.novare.natflax.NatflaxAdvance.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EpisodeServiceImpl implements EpisodeService {

    @Autowired
    private EpisodeRepo episodeRepo;

    @Autowired
    private SeriesRepo seriesRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    private final IStorageService iStorageService;

    public EpisodeServiceImpl(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

    @Override
    public EpisodeDto createEpisode(EpisodeDto episodeDto, Integer seriesId) {

        String logMessage;
        if(episodeDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String thumbDataBytes = FileUtil.getImageFromBase64(episodeDto.getThumbnail_url());
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: " + " ---" + complete_thumb_URL;
            log.info(logMessage);
            episodeDto.setThumbnail_url(complete_thumb_URL);
        }

        Series series = this.seriesRepo.findById(seriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Series", "series id ", seriesId));

        Episode episode = this.modelMapper.map(episodeDto, Episode.class);

        episode.setSeries(series);
        episode.setTitle(episodeDto.getTitle());
        episode.setEpisode_no(episodeDto.getEpisode_no());
        episode.setSeason_no(episodeDto.getSeason_no());
        episode.setGenre_id(episodeDto.getGenre_id());
        episode.setSummary(episodeDto.getSummary());
        episode.setVideo_code(episodeDto.getVideo_code());

        Episode newEpisode = this.episodeRepo.save(episode);
        return this.modelMapper.map(newEpisode, EpisodeDto.class);
    }
}
