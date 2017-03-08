package com.webtechnology.service;

import com.webtechnology.model.Region;

import java.util.List;

/**
 * Created by IO on 27.10.2016.
 */
public interface RegionService {

    Region findById(long id);

    Region create(Region region);

    long delete(Region region);

    List<Region> getAll();

    long update(Region region);

    List<Region> getValuesForFilter();

    List<Region> getValuesForFilter(List<Long> styles, List<Long> themes);

}
