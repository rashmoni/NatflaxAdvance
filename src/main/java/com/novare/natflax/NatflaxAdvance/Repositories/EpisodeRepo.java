package com.novare.natflax.NatflaxAdvance.Repositories;

import com.novare.natflax.NatflaxAdvance.Entity.Episode;
import com.novare.natflax.NatflaxAdvance.Entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepo extends JpaRepository<Episode, Integer> {

    List<Episode> findBySeries(Series series);
}
