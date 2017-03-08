package com.webtechnology.service;

import com.webtechnology.model.Trip;
import com.webtechnology.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip findById(long id) {
        return tripRepository.findOne(id);
    }

    @Override
    public Trip create(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public long delete(Trip trip) {
        Trip deletedTrip = tripRepository.findOne(trip.getId());
        if (null == deletedTrip){
            return -1;
        }else {
            tripRepository.delete(trip);
            return trip.getId();
        }
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public long update(Trip trip) {
        Trip deletedTrip = tripRepository.findOne(trip.getId());
        if (null == deletedTrip){
            return -1;
        }else {
            tripRepository.save(trip);
            return trip.getId();
        }
    }

    @Override
    public List<Trip> getFilteredTrips(List<Long> regions, List<Long> styles, List<Long> themes) {
        return tripRepository.getFilteredTrips(regions, styles, themes);
    }
}
