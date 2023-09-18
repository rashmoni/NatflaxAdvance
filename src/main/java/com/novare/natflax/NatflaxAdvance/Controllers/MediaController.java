package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/media")
@Log4j2
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/")
    public ResponseEntity<List<MediaDto>> createMovie() {
        String message = "User requested all media ";
        log.info(message);
        List<MediaDto> listOfAllMedia = this.mediaService.getAllMedia();
        return new ResponseEntity<>(listOfAllMedia, HttpStatus.OK);
    }

}
