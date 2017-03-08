package com.webtechnology.service;

import com.webtechnology.model.Style;
import com.webtechnology.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Override
    public Style findById(long id) {
        return styleRepository.findOne(id);
    }

    @Override
    public Style create(Style style) {
        return styleRepository.save(style);
    }

    @Override
    public long delete(Style style) {
        Style deletedStyle = styleRepository.findOne(style.getId());
        if (null == deletedStyle) {
            return -1;
        } else {
            styleRepository.delete(deletedStyle);
            return style.getId();
        }
    }

    @Override
    public List<Style> getAll() {
        return styleRepository.findAll();
    }

    @Override
    public long update(Style style) {
        Style updatedStyle = styleRepository.findOne(style.getId());
        if (null == updatedStyle) {
            return -1;
        } else {
            styleRepository.save(updatedStyle);
            return style.getId();
        }
    }

    @Override
    public List<Style> getValuesForFilter() {
        return styleRepository.getValuesForFilter();
    }

    @Override
    public List<Style> getValuesForFilter(List<Long> regions, List<Long> themes) {
        return styleRepository.getFilteredValuesForFilter(regions, themes);
    }
}
