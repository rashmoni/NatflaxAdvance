package com.novare.natflax.NatflaxAdvance.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DocumentoryDto {
    private int document_id;

    private int media_type_id = 3;
    private String banner_url;
    private String thumbnail_url;
    private String title;
    private String video_code;
    private String narrator;
    private String summary;
    private int genre_id;

}
