package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.EpisodeDto;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;
import com.novare.natflax.NatflaxAdvance.Services.EpisodeService;
import com.novare.natflax.NatflaxAdvance.Services.SeriesService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @PostMapping("/{seriesId}/episode/")
    public ResponseEntity<EpisodeDto> createSeries(@Valid @RequestBody EpisodeDto episodeDto, @PathVariable Integer seriesId) {
        String message = "User tried to create new item with name: " + episodeDto.getTitle();
        log.info(message);

        EpisodeDto createEpisodeDto = this.episodeService.createEpisode(episodeDto, seriesId);
        return new ResponseEntity<>(createEpisodeDto, HttpStatus.CREATED);
    }
}
