package com.webtechnology.service;

import com.webtechnology.model.Theme;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
public interface ThemeService {

    Theme findById(long id);

    Theme create(Theme theme);

    long delete(Theme theme);

    List<Theme> getAll();

    long update(Theme theme);

    List<Theme> getThemeFilters();

    List<Theme> getValuesForFilter(List<Long> styles, List<Long> regions);
}
