package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.UserDto;

import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);

    void deleteMovie(Integer uid);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(Integer movieId);
}
