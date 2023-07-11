package com.novare.natflax.NatflaxAdvance.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "episode")
@Getter
@Setter
@NoArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int episode_id;

    private String thumbnail_url;
    private String title;
    private String video_code;
    private int season_no;
    private int episode_no;
    private String summary;
    private int genre_id;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
}
