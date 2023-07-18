package com.novare.natflax.NatflaxAdvance.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="serieses")
@NoArgsConstructor
@Getter
@Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seriesID;

    private int media_type_id;
    private String banner_url;
    private String thumbnail_url;
    private String title;
    private String summary;
    private int genre_id;

    @OneToMany(mappedBy = "series",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Episode> episode = new ArrayList<>();

}
