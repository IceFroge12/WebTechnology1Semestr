package com.webtechnology.repository;

import com.webtechnology.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("SELECT new Theme(th.id, th.title ,count(t.id)) FROM Theme th JOIN trip t on th.id = t.theme.id  GROUP BY th.id")
    List<Theme> getValuesForFilter();

    @Query("SELECT new Theme(th.id, th.title ,count(t.id)) FROM Theme th JOIN trip t on th.id = t.theme.id WHERE (t.theme.id in :styles or :styles is null) and (t.theme.id in :regions or :regions is null) GROUP BY th.id")
    List<Theme> getFilteredValuesForFilter(@Param("styles") List<Long> styles,
                                           @Param("regions") List<Long> regions);
}
