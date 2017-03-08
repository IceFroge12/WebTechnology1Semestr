package com.webtechnology.controller;

import com.webtechnology.dto.FilteredListDTO;
import com.webtechnology.model.Trip;
import com.webtechnology.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/tripPreviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Trip>> getAllTripPreview(){
        List<Trip> trips = tripService.getAll();
        return ResponseEntity.ok(trips);
    }

    @RequestMapping(value = "/filterTrip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Trip>> getFilteredList(@RequestBody FilteredListDTO filteredListDTO){
        List<Trip> trips = tripService.getFilteredTrips(filteredListDTO.getRegionsId(), filteredListDTO.getStylesId(),filteredListDTO.getThemesId());
        return ResponseEntity.ok(trips);
    }
}










