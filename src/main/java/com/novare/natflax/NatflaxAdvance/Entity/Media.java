package com.novare.natflax.NatflaxAdvance.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="media")
@NoArgsConstructor
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
