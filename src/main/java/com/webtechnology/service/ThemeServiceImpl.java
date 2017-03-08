package com.webtechnology.service;

import com.webtechnology.model.Theme;
import com.webtechnology.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Service
public class ThemeServiceImpl implements ThemeService {


    private ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Theme findById(long id) {
        return themeRepository.findOne(id);
    }

    @Override
    public Theme create(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public long delete(Theme theme) {
        Theme deletedTheme = themeRepository.findOne(theme.getId());
        if (null == deletedTheme){
            return -1;
        }else {
            themeRepository.delete(theme);
            return theme.getId();
        }
    }

    @Override
    public List<Theme> getAll() {
        return themeRepository.findAll();
    }

    @Override
    public long update(Theme theme) {
        Theme deletedTheme = themeRepository.findOne(theme.getId());
        if (null == deletedTheme){
            return -1;
        }else {
            themeRepository.save(theme);
            return theme.getId();
        }
    }

    @Override
    public List<Theme> getThemeFilters() {
        return themeRepository.getValuesForFilter();
    }

    @Override
    public List<Theme> getValuesForFilter(List<Long> styles, List<Long> regions) {
        return themeRepository.getFilteredValuesForFilter(styles, regions);
    }
}
