package com.webtechnology.dto;

import com.webtechnology.model.Region;
import com.webtechnology.model.Style;
import com.webtechnology.model.Theme;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IO on 30.10.2016.
 */
public class FilterAttributesDTO implements Serializable {
    private List<Region> regionFilterDTOs;
    private List<Style> styleFilterDTOs;
    private List<Theme> themeFilterDTOs;

    public FilterAttributesDTO() {

    }

    public List<Region> getRegionFilterDTOs() {
        return regionFilterDTOs;
    }

    public void setRegionFilterDTOs(List<Region> regionFilterDTOs) {
        this.regionFilterDTOs = regionFilterDTOs;
    }

    public List<Style> getStyleFilterDTOs() {
        return styleFilterDTOs;
    }

    public void setStyleFilterDTOs(List<Style> styleFilterDTOs) {
        this.styleFilterDTOs = styleFilterDTOs;
    }

    public List<Theme> getThemeFilterDTOs() {
        return themeFilterDTOs;
    }

    public void setThemeFilterDTOs(List<Theme> themeFilterDTOs) {
        this.themeFilterDTOs = themeFilterDTOs;
    }

    @Override
    public String toString() {
        return "FilterAttributesDTO{" +
                "regionFilterDTOs=" + regionFilterDTOs +
                ", styleFilterDTOs=" + styleFilterDTOs +
                ", themeFilterDTOs=" + themeFilterDTOs +
                '}';
    }
}
