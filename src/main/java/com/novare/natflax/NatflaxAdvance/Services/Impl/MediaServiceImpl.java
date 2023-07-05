package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Media;
import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Repositories.MediaRepo;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepo mediaRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MediaDto createMedia(MediaDto mediaDto) {
        Media media = this.dtoToMedia(mediaDto);
        Media savedMedia = this.mediaRepo.save(media);
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
