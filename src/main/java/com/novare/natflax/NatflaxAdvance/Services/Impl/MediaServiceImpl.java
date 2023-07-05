package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Media;
import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Repositories.MediaRepo;
import com.novare.natflax.NatflaxAdvance.Services.FileSystemStorageService;
import com.novare.natflax.NatflaxAdvance.Services.IStorageService;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import com.novare.natflax.NatflaxAdvance.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepo mediaRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    FileSystemStorageService fileSystemStorageService;

    private final IStorageService iStorageService;

    public MediaServiceImpl(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }


    @Override
    public MediaDto createMedia(MediaDto mediaDto) {
        String message;

        if(mediaDto.getBanner_url() != null || mediaDto.getThumbnail_url() != null){
            message = "trying to convert base64 image and store it to filesystem";
            log.info(message);

            String bannerDataBytes = FileUtil.getImageFromBase64(mediaDto.getBanner_url());
            String thumbDataBytes = FileUtil.getImageFromBase64(mediaDto.getThumbnail_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9000/files/";
            String complete_banner_URL = baseURL + bannerURL;
            String complete_thumb_URL = thumbURL + bannerURL;

            message = "image successfully stored, image url is: "+ complete_banner_URL + " ---" + complete_thumb_URL;
            log.info(message);
            mediaDto.setBanner_url(complete_banner_URL);
            mediaDto.setThumbnail_url(complete_thumb_URL);
        }
        Media media = this.dtoToMedia(mediaDto);
        Media savedMedia = this.mediaRepo.save(media);
        message = "AuctionItem is save in database";
        log.info(message);
        return this.mediaToDto(savedMedia);
    }


    private Media dtoToMedia(MediaDto mediaDto) {
        Media media = this.modelMapper.map(mediaDto,Media.class);
        return media;
    }

    public MediaDto mediaToDto(Media media) {
        MediaDto mediaDto = this.modelMapper.map(media,MediaDto.class);
        return mediaDto;
    }
}
