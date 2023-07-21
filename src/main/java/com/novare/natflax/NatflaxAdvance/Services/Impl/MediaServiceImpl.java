package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Documentary;
import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import com.novare.natflax.NatflaxAdvance.Entity.Series;
import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Repositories.DocumentoryRepo;
import com.novare.natflax.NatflaxAdvance.Repositories.MovieRepo;
import com.novare.natflax.NatflaxAdvance.Repositories.SeriesRepo;
import com.novare.natflax.NatflaxAdvance.Services.MediaService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private SeriesRepo seriesRepo;

    @Autowired
    private DocumentoryRepo documentoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<MediaDto> getAllMedia() {
        List<Movie> allMovies = this.movieRepo.findAll();
        List<Series> allSeries = this.seriesRepo.findAll();
        List<Documentary> allDocumentaries = this.documentoryRepo.findAll();

        List<MediaDto> mediaDtos = allMovies.stream().map(movie -> this.movieToMediaDto(movie)).collect(Collectors.toList());
        mediaDtos.addAll(allSeries.stream().map(series -> this.seriesToMediaDto(series)).collect(Collectors.toList()));
        mediaDtos.addAll(allDocumentaries.stream().map(documentary -> this.documentaryToMediaDto(documentary)).collect(Collectors.toList()));


        return mediaDtos;
    }

    public MediaDto movieToMediaDto(Movie movie) {
        MediaDto mediaDto = this.modelMapper.map(movie, MediaDto.class);
        return mediaDto;
    }

    public MediaDto seriesToMediaDto(Series series) {
        MediaDto mediaDto = this.modelMapper.map(series, MediaDto.class);
        return mediaDto;
    }

    public MediaDto documentaryToMediaDto(Documentary documentory) {
        MediaDto mediaDto = this.modelMapper.map(documentory, MediaDto.class);
        return mediaDto;
    }


}


