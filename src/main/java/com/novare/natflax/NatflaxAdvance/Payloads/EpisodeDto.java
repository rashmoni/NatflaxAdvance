package com.novare.natflax.NatflaxAdvance.Payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novare.natflax.NatflaxAdvance.Entity.Series;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EpisodeDto {
    @JsonIgnore
    private Integer episodeID;
    private String thumbnail_url;
    private String title;
    private String video_code;
    private int season_no;
    private int episode_no;
    private String summary;
    private int genre_id;

    @JsonIgnore
    private Series series;
}
