package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="Tides info from USGS")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @ApiOperation(value = "Get tides information between begin date and end date", notes = "JSON return format documented here: https://api.tidesandcurrents.noaa.gov/api/prod/")
    @GetMapping("/get")
    public ResponseEntity<String> getEarthquakes(
        @ApiParam("beginDate") @RequestParam String beginDate,
        @ApiParam("endDate") @RequestParam String endDate,
        @ApiParam("station") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getEarthquakes: beginDate={} endDate={} station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }

}
