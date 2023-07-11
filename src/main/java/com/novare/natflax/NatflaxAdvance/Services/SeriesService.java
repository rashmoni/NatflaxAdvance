package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;

public interface SeriesService {
    SeriesDto createSeries(SeriesDto seriesDto);
}
