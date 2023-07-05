package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Payloads.UserDto;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import com.novare.natflax.NatflaxAdvance.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;


    @PostMapping("/")
    public ResponseEntity<MediaDto> createMedia(@Valid @RequestBody MediaDto mediaDto){
        MediaDto createMediaDto =this.mediaService.createMedia(mediaDto);
        return  new ResponseEntity<>(createMediaDto, HttpStatus.CREATED);
    }
}
