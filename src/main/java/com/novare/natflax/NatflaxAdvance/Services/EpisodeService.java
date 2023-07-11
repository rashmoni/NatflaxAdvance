package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Payloads.EpisodeDto;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;

public interface EpisodeService {

    EpisodeDto createEpisode(EpisodeDto episodeDto, Integer seriesId);
}
