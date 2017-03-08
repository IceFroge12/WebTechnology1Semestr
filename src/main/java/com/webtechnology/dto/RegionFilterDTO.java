package com.webtechnology.dto;

import java.io.Serializable;

/**
 * Created by IO on 02.11.2016.
 */
public class RegionFilterDTO implements Serializable {

    private long id;
    private String title;
    private int amount;

    public RegionFilterDTO(){

    }

    public RegionFilterDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public RegionFilterDTO(long id, String title, int amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "RegionFilterDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }
}
