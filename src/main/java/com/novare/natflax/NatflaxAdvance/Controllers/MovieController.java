package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.UserDto;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
@Log4j2
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/movie/create")
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        String message = "User tried to create new item with name: " + movieDto.getTitle();
        log.info(message);

        MovieDto createMovieDto = this.movieService.createMovie(movieDto);
        return new ResponseEntity<>(createMovieDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable("movieId") Integer uid){
        movieService.deleteMovie(uid);
        return new ResponseEntity(new ApiResponse("Movie deleted successfully",true), HttpStatus.OK);
    }
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<MovieDto> getSingleMovie(@PathVariable Integer movieId){
        return ResponseEntity.ok(this.movieService.getMovieById(movieId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/movie/update")
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movieDto){
        return ResponseEntity.ok(this.movieService.updateMovie(movieDto));
    }

}

