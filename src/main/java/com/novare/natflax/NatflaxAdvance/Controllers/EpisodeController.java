package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.EpisodeDto;
import com.novare.natflax.NatflaxAdvance.Services.EpisodeService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @PostMapping("/{seriesId}/episode")
    public ResponseEntity<EpisodeDto> createEpisode(@Valid @RequestBody EpisodeDto episodeDto, @PathVariable Integer seriesId) {
        String message = "User tried to create new item with name: " + episodeDto.getTitle();
        log.info(message);

        EpisodeDto createEpisodeDto = this.episodeService.createEpisode(episodeDto, seriesId);
        return new ResponseEntity<>(createEpisodeDto, HttpStatus.CREATED);
    }

    @PutMapping("/episode/update")
    public ResponseEntity<EpisodeDto> updateEpisode(@Valid @RequestBody EpisodeDto episodeDto) {
        String message = "User tried to create new item with name: " + episodeDto.getTitle();
        log.info(message);

        EpisodeDto updatedEpisodeDto = this.episodeService.updateEpisode(episodeDto);
        return new ResponseEntity<>(updatedEpisodeDto, HttpStatus.CREATED);
    }


    @GetMapping("/{seriesId}/episodes")
    public ResponseEntity<List<EpisodeDto>> getEpisodeBySeries(@PathVariable Integer seriesId) {
        String message = "User tried to create new item with name: ";
        log.info(message);

        List<EpisodeDto> allEpisodesForSeries = this.episodeService.getEpisodeBySeries(seriesId);
        return new ResponseEntity<>(allEpisodesForSeries, HttpStatus.OK);
    }

    @DeleteMapping("/episodes/{episodeId}")
    public ResponseEntity<ApiResponse> deleteEpisode(@PathVariable Integer episodeId) {
        String message = "User tried to delete an Episode";
        log.info(message);

        episodeService.deleteEpisode(episodeId);
        return new ResponseEntity(new ApiResponse("Episode deleted successfully",true), HttpStatus.OK);
    }
}
