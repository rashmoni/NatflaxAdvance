package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.DocumentaryDto;
import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;
import com.novare.natflax.NatflaxAdvance.Services.DocumentaryService;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
import com.novare.natflax.NatflaxAdvance.Services.SeriesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media/")
@Log4j2
public class MediaController {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private MovieService movieService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private DocumentaryService documentaryService;

    @GetMapping("/")
    public ResponseEntity<List<MediaDto>> getAllMedia() {
        String message = "User requested all media ";
        log.info(message);
        List<MediaDto> listOfAllMedia = this.mediaService.getAllMedia();
        return new ResponseEntity<>(listOfAllMedia, HttpStatus.OK);
    }

    @GetMapping("movies/")
    public ResponseEntity<List<MovieDto>> getAllMovie() {
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("tv-series/")
    public ResponseEntity<List<SeriesDto>> getAllSeries(){
        return ResponseEntity.ok(this.seriesService.getAllSeries());
    }

    @GetMapping("/documentaries/")
    public ResponseEntity<List<DocumentaryDto>> getAllDocumentary(){
        return ResponseEntity.ok(this.documentaryService.getAllDocumentaries());
    }

}
