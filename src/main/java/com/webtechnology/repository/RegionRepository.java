package com.webtechnology.repository;

import com.webtechnology.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IO on 27.10.2016.
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("SELECT new Region (r.id, r.title ,count(t.id)) FROM Region r JOIN trip t on r.id = t.region.id  GROUP BY r.id")
    List<Region> getValuesForFilter();

    @Query("SELECT new Region (r.id, r.title ,count(t.id)) FROM Region r JOIN trip t on r.id = t.region.id WHERE (t.style.id in :styles or :styles is null) and (t.theme.id in :themes or :themes is null) GROUP BY r.id")
    List<Region> getFilteredValuesForFilter(@Param("styles") List<Long> styles,
                                           @Param("themes") List<Long> themes);

}
