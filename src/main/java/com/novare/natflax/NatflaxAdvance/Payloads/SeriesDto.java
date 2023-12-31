package com.novare.natflax.NatflaxAdvance.Payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SeriesDto {
    private int id;

    private int media_type_id = 1;
    private String banner_url;
    private String thumbnail_url;
    private String title;
    private String summary;
    private int genre_id;
}
