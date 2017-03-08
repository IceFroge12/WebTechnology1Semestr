package com.webtechnology.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by IO on 12.11.2016.
 */
@Entity
public class City implements Serializable {

    @Id
    private long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private Country country;

    public City() {
    }

    public City(long id, String title, Country country) {
        this.id = id;
        this.title = title;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", country=" + country +
                '}';
    }
}
