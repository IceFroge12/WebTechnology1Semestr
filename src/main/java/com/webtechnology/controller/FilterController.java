package com.webtechnology.controller;

import com.webtechnology.dto.FilterAttributesDTO;
import com.webtechnology.dto.FilteredListDTO;
import com.webtechnology.model.Region;
import com.webtechnology.model.Style;
import com.webtechnology.model.Theme;
import com.webtechnology.service.RegionService;
import com.webtechnology.service.StyleService;
import com.webtechnology.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by IO on 27.10.2016.
 */
@Controller
public class FilterController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private StyleService styleService;

    @RequestMapping(value = "/filterAttributes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilterAttributesDTO> getAllFilters() {

        List<Region> regions = (List<Region>)regionService.getValuesForFilter();
        List<Style> styles = styleService.getValuesForFilter();
        List<Theme> themes = themeService.getThemeFilters();

        FilterAttributesDTO filterAttributesDTO = new FilterAttributesDTO();
        filterAttributesDTO.setRegionFilterDTOs(regions);
        filterAttributesDTO.setStyleFilterDTOs(styles);
        filterAttributesDTO.setThemeFilterDTOs(themes);
        return ResponseEntity.ok(filterAttributesDTO);
    }

    @RequestMapping(value = "/filterAttributes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilterAttributesDTO> getAllFilters(@RequestBody(required = false) FilteredListDTO filteredListDTO){
        if (null != filteredListDTO){
            FilterAttributesDTO filterAttributesDTO;
            filterAttributesDTO = new FilterAttributesDTO();
            List<Style> styles = styleService.getValuesForFilter(filteredListDTO.getRegionsId(), filteredListDTO.getThemesId());
            List<Region> regions = regionService.getValuesForFilter(filteredListDTO.getStylesId(), filteredListDTO.getThemesId());
            List<Theme> themes = themeService.getValuesForFilter(filteredListDTO.getStylesId(), filteredListDTO.getRegionsId());
            filterAttributesDTO.setStyleFilterDTOs(styles);
            filterAttributesDTO.setRegionFilterDTOs(regions);
            filterAttributesDTO.setThemeFilterDTOs(themes);
            return ResponseEntity.ok(filterAttributesDTO);
        }else {
            return ResponseEntity.ok(null);
        }

    }


}
