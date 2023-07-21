package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.json.simple.JSONObject;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies")
@Log4j2
public class MovieController {
    @Autowired
    private MovieService movieService;

    @CrossOrigin(origins = "http://localhost:3000/*")
    @PostMapping("/create")
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        String message = "User tried to create new item with name: " + movieDto.getTitle();
        log.info(message);

        MovieDto createdMovieDto = this.movieService.createMovie(movieDto);
        return new ResponseEntity<>(createdMovieDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @CrossOrigin(origins = "http://localhost:3000/*")
    @PutMapping("/update")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody JSONObject payload){
        return ResponseEntity.ok(this.movieService.updateMovie(payload));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getSingleMovie(@PathVariable Integer movieId){
        return ResponseEntity.ok(this.movieService.getMovieById(movieId));
    }
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable("movieId") Integer movieId){
        movieService.deleteMovie(movieId);
        return new ResponseEntity(new ApiResponse("Movie deleted successfully",true), HttpStatus.OK);
    }
}

