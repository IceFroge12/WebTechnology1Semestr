package com.webtechnology.service;

import com.webtechnology.model.Region;
import com.webtechnology.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IO on 27.10.2016.
 */

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    private RegionServiceImpl regionService;

    public RegionService getInstance(){
        if (regionService == null){
            this.regionService = new RegionServiceImpl();
            return this.regionService;
        }else {
            return regionService;
        }
    }

    @Override
    public Region findById(long id) {
        return regionRepository.findOne(id);
    }

    @Override
    public Region create(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public long delete(Region region) {
        Region deletedRegion = regionRepository.findOne(region.getId());
        if (null == deletedRegion) {
            return -1;
        } else {
            regionRepository.delete(deletedRegion);
            return region.getId();
        }
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public long update(Region region) {
        Region updatedRegion = regionRepository.findOne(region.getId());
        if (null == updatedRegion) {
            return -1;
        } else {
            regionRepository.save(updatedRegion);
            return region.getId();
        }
    }

    @Override
    public List<Region> getValuesForFilter() {
        return regionRepository.getValuesForFilter();
    }

    @Override
    public List<Region> getValuesForFilter(List<Long> styles, List<Long> themes) {
        return regionRepository.getFilteredValuesForFilter(styles, themes);
    }
}
