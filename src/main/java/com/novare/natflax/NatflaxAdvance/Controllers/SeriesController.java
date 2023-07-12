package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.SeriesDto;
import com.novare.natflax.NatflaxAdvance.Services.SeriesService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/series")

public class SeriesController{

    @Autowired
    private SeriesService seriesService;

    @PostMapping("/")
    public ResponseEntity<SeriesDto> createSeries(@Valid @RequestBody SeriesDto seriesDto) {
        String message = "User tried to create new item with name: " + seriesDto.getTitle();
        log.info(message);

        SeriesDto createSeriesDto = this.seriesService.createSeries(seriesDto);
        return new ResponseEntity<>(createSeriesDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<SeriesDto>> getAllSeries(){
        return ResponseEntity.ok(this.seriesService.getAllSeries());
    }

    @PutMapping("/")
    public ResponseEntity<SeriesDto> updateSeries(@Valid @RequestBody SeriesDto seriesDto){
        return ResponseEntity.ok(this.seriesService.updateSeries(seriesDto));
    }

    @GetMapping("/{seriesID}")
    public ResponseEntity<SeriesDto> getSeriesById(@PathVariable Integer seriesID){
        return ResponseEntity.ok(this.seriesService.getSeriesById(seriesID));
    }
    @DeleteMapping("/{seriesID}")
    public ResponseEntity<ApiResponse> deleteSeries(@PathVariable("seriesID") Integer seriesID){
        seriesService.deleteSeries(seriesID);
        return new ResponseEntity(new ApiResponse("Series deleted successfully",true), HttpStatus.OK);
    }

}
