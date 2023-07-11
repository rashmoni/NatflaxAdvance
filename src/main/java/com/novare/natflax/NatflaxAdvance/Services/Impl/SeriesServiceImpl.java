package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Series;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;
import com.novare.natflax.NatflaxAdvance.Repositories.SeriesRepo;
import com.novare.natflax.NatflaxAdvance.Services.FileSystemStorageService;
import com.novare.natflax.NatflaxAdvance.Services.IStorageService;
import com.novare.natflax.NatflaxAdvance.Services.SeriesService;
import com.novare.natflax.NatflaxAdvance.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesRepo SeriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    FileSystemStorageService fileSystemStorageService;
    private final IStorageService iStorageService;

    public SeriesServiceImpl(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

    @Override
    public SeriesDto createSeries(SeriesDto seriesDto) {
        String logMessage;
        if(seriesDto.getBanner_url() != null || seriesDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(seriesDto.getBanner_url());
            String thumbDataBytes = FileUtil.getImageFromBase64(seriesDto.getThumbnail_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL + " ---" + complete_thumb_URL;
            log.info(logMessage);
            seriesDto.setBanner_url(complete_banner_URL);
            seriesDto.setThumbnail_url(complete_thumb_URL);
        }
        Series series = this.dtoToSeries(seriesDto);
        Series savedSeries = this.SeriesRepo.save(series);
        logMessage = "Series is saved in database";
        log.info(logMessage);
        return this.seriesToDto(savedSeries);
    }


    private Series dtoToSeries(SeriesDto seriesDto) {
        Series series = this.modelMapper.map(seriesDto, Series.class);
        return series;
    }

    public SeriesDto seriesToDto(Series series) {
        SeriesDto seriesDto = this.modelMapper.map(series, SeriesDto.class);
        return seriesDto;
    }
}
