package com.webtechnology.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IO on 01.11.2016.
 */
@Entity(name = "country")
public class Country implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    @BatchSize(size = 4)
    @Fetch(FetchMode.SELECT)
    private List<City> cities;

    public Country() {
    }

    public Country(long id, String title) {
        this.id = id;
        this.title = title;
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
}
