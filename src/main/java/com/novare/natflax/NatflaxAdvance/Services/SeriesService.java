package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;

import java.util.List;

public interface SeriesService {
    SeriesDto createSeries(SeriesDto seriesDto);

    List<SeriesDto> getAllSeries();

    SeriesDto updateSeries(SeriesDto seriesDto);

    SeriesDto getSeriesById(Integer seriesID);

    void deleteSeries(Integer seriesID);
}
