package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import com.novare.natflax.NatflaxAdvance.Entity.User;
import com.novare.natflax.NatflaxAdvance.Exceptions.ResourceNotFoundException;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.UserDto;
import com.novare.natflax.NatflaxAdvance.Repositories.MovieRepo;
import com.novare.natflax.NatflaxAdvance.Services.FileSystemStorageService;
import com.novare.natflax.NatflaxAdvance.Services.IStorageService;
import com.novare.natflax.NatflaxAdvance.Services.MovieService;
import com.novare.natflax.NatflaxAdvance.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    private final IStorageService iStorageService;

    public MovieServiceImpl(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto ) {
        String logMessage;
        if(movieDto.getBanner_url() != null || movieDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(movieDto.getBanner_url());
            String thumbDataBytes = FileUtil.getImageFromBase64(movieDto.getThumbnail_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL + " ---" + complete_thumb_URL;
            log.info(logMessage);
            movieDto.setBanner_url(complete_banner_URL);
            movieDto.setThumbnail_url(complete_thumb_URL);
        }
        Movie movie = this.dtoToMovie(movieDto);
        Movie savedMovie = this.movieRepo.save(movie);
        logMessage = "Movie is saved in database";
        log.info(logMessage);
        return this.movieToDto(savedMovie);
    }

    @Override
    public void deleteMovie(Integer mid) {
        Movie movie = this.movieRepo.findById(mid).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", mid));
        this.movieRepo.delete(movie);

    }
    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies= this.movieRepo.findAll();
        List<MovieDto> userDtos = movies.stream().map(user -> this.movieToDto(user)).collect(Collectors.toList());
        return userDtos;
    }
    @Override
    public MovieDto getMovieById(Integer movieId) {
        Movie movie = this.movieRepo.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", movieId));
        return this.movieToDto(movie);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        String logMessage;

        Movie movie = this.movieRepo.findByTitle(movieDto.getTitle()).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", movieDto.getId()));

        Integer movieId = movie.getId();

        if(!movieDto.getBanner_url().startsWith("http")){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(movieDto.getBanner_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL;
            log.info(logMessage);
            movieDto.setBanner_url(complete_banner_URL);
        }

        if(!movieDto.getThumbnail_url().startsWith("http")){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String thumbDataBytes = FileUtil.getImageFromBase64(movieDto.getThumbnail_url());
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: " + " ---" + complete_thumb_URL;
            log.info(logMessage);
            movieDto.setThumbnail_url(complete_thumb_URL);
        }



        movie = this.dtoToMovie(movieDto);
        movie.setId(movieId);

        Movie savedMovie = this.movieRepo.save(movie);
        logMessage = "Movie is updated!";
        log.info(logMessage);
        return this.movieToDto(savedMovie);
    }

    private Movie dtoToMovie(MovieDto movieDto) {
        Movie movie = this.modelMapper.map(movieDto, Movie.class);
        return movie;
    }

    public MovieDto movieToDto(Movie movie) {
        MovieDto movieDto = this.modelMapper.map(movie, MovieDto.class);
        return movieDto;
    }
}
