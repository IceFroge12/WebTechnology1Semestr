package com.webtechnology.service;

import com.webtechnology.model.Style;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
public interface StyleService {
    Style findById(long id);

    Style create(Style style);

    long delete(Style style);

    List<Style> getAll();

    long update(Style style);

    List<Style> getValuesForFilter();

    List<Style> getValuesForFilter(List<Long> regions, List<Long> themes);
}
