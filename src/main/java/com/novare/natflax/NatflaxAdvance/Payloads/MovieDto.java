package com.novare.natflax.NatflaxAdvance.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieDto {

    private int media_id;
    private String banner_url;
    private String thumbnail_url;
    private String title;
    private String video_code;
    private String director;
    private String summary;
    private int genre_id;
    private int rating;

}
