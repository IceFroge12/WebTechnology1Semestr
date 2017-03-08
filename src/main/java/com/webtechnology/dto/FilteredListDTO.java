package com.webtechnology.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IO on 05.11.2016.
 */

public class FilteredListDTO implements Serializable {

    @JsonProperty("_stylesId")
    private List<Long> stylesId;

    @JsonProperty("_themesId")
    private List<Long> themesId;

    @JsonProperty("_regionsId")
    private List<Long> regionsId;

    public FilteredListDTO() {

    }

    public FilteredListDTO(List<Long> stylesId, List<Long> themesId, List<Long> regionsId) {
        this.stylesId = stylesId;
        this.themesId = themesId;
        this.regionsId = regionsId;
    }

    public List<Long> getStylesId() {
        return stylesId;
    }

    public void setStylesId(List<Long> stylesId) {
        if (0 == stylesId.size()){
            this.stylesId = null;
        }else {
            this.stylesId = stylesId;
        }
    }

    public List<Long> getThemesId() {
        return themesId;
    }

    public void setThemesId(List<Long> themesId) {
        if (0 == themesId.size()){
            this.themesId = null;
        }else {
            this.themesId = themesId;
        }
    }

    public List<Long> getRegionsId() {
        return regionsId;
    }

    public void setRegionsId(List<Long> regionsId) {
        if (0 == regionsId.size()){
            this.regionsId = null;
        }else {
            this.regionsId = regionsId;
        }
    }

    @Override
    public String toString() {
        return "FilteredListDTO{" +
                "stylesId=" + stylesId +
                ", themesId=" + themesId +
                ", regionsId=" + regionsId +
                '}';
    }
}
