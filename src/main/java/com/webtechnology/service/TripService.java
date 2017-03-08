package com.webtechnology.service;

import com.webtechnology.model.Trip;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
public interface TripService {

    Trip findById(long id);

    Trip create(Trip trip);

    long delete(Trip trip);

    List<Trip> getAll();

    long update(Trip trip);

    List<Trip> getFilteredTrips(List<Long> regions, List<Long> styles, List<Long> themes);
}
