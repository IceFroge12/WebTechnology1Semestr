package com.webtechnology.repository;

import com.webtechnology.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
public interface StyleRepository extends JpaRepository<Style,Long> {

    @Query("SELECT new Style(s.id, s.title ,count(t.id)) FROM Style s JOIN trip t on s.id = t.style.id  GROUP BY s.id")
    List<Style> getValuesForFilter();


    @Query("SELECT new Style(s.id, s.title ,count(t.id)) FROM Style s JOIN trip t on s.id = t.style.id WHERE (t.region.id in :regions or :regions is null) and (t.theme.id in :themes or :themes is null) GROUP BY s.id")
    List<Style> getFilteredValuesForFilter(@Param("regions") List<Long> regions,
                                           @Param("themes") List<Long> themes);

}
