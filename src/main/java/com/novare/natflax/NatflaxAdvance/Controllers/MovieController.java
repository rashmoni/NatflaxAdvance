package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
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
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/")
    public ResponseEntity<MovieDto> createMedia(@Valid @RequestBody MovieDto movieDto){
        String message = "User tried to create new item witth name: " +  movieDto.getTitle() ;
        log.info(message);

        MovieDto createMovieDto = this.movieService.createMovie(movieDto);
        return  new ResponseEntity<>(createMovieDto, HttpStatus.CREATED);
    }
}
