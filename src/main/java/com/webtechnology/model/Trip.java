package com.webtechnology.model;


import com.webtechnology.dto.RegionFilterDTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@SqlResultSetMapping(name = "TripInfoMapping",
        entities = {
                @EntityResult(
                        entityClass = Trip.class
                ),
        },
        classes = {
                @ConstructorResult(targetClass = RegionFilterDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "amount", type = Long.class)
                        }
                )

        }
)
@Entity(name = "trip")
@Cacheable(false)
public class Trip implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;


    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_style")
    private Style style;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_startpoint")
    private Country startPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_endpoint")
    private Country endPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_region")
    private Region region;

    @Column(name = "duration")
    private int duration;

    @Column(name = "short_description")
    private String shortDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "tripcountry",
            joinColumns = {@JoinColumn(name = "id_trip", referencedColumnName = "id", unique = true)},
            inverseJoinColumns = {@JoinColumn(name = "id_country", referencedColumnName = "id", unique = true)}
    )
    private List<Country> countries;

    public Trip() {
    }

    public Trip(long id, String title, Theme theme, Style style, Country startPoint, Country endPoint, Region region, int duration, String shortDescription, List<Country> countries) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.style = style;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.region = region;
        this.duration = duration;
        this.shortDescription = shortDescription;
        this.countries = countries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Country getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Country startPoint) {
        this.startPoint = startPoint;
    }

    public Country getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Country endPoint) {
        this.endPoint = endPoint;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", theme=" + theme +
                ", style=" + style +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", region=" + region +
                ", duration=" + duration +
                ", shortDescription='" + shortDescription + '\'' +
                ", countries=" + countries +
                '}';
    }
}
