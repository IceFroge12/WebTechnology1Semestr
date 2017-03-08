package com.webtechnology.dto;

import com.webtechnology.model.Trip;

import java.io.Serializable;

/**
 * Created by IO on 08.11.2016.
 */
public class TripInfoDTO implements Serializable {

    private Trip trip;
    private RegionFilterDTO regionFilterDTO;


    public TripInfoDTO() {
    }

    public TripInfoDTO(Trip trip) {
        this.trip = trip;
    }

    public TripInfoDTO(Trip trip, RegionFilterDTO regionFilterDTO) {
        this.trip = trip;
        this.regionFilterDTO = regionFilterDTO;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public RegionFilterDTO getRegionFilterDTO() {
        return regionFilterDTO;
    }

    public void setRegionFilterDTO(RegionFilterDTO regionFilterDTO) {
        this.regionFilterDTO = regionFilterDTO;
    }
}
