package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/media")
@Log4j2
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/")
    public ResponseEntity<MediaDto> createMedia(@Valid @RequestBody MediaDto mediaDto){
        String message = "User tried to create new item witth name: " +  mediaDto.getTitle() ;
        log.info(message);

        MediaDto createMediaDto = this.mediaService.createMedia(mediaDto);
        return  new ResponseEntity<>(createMediaDto, HttpStatus.CREATED);
    }
}
