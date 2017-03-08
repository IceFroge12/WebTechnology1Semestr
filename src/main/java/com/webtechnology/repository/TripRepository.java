package com.webtechnology.repository;

import com.webtechnology.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{



    @Query("select t from trip t where (t.region.id in :regions or :regions is null) and (t.style.id in :styles or :styles is null) and (t.theme.id in :themes or :themes is null)")
    List<Trip> getFilteredTrips(@Param("regions") List<Long> regions,
                                @Param("styles") List<Long> styles,
                                @Param("themes") List<Long> themes);
}
