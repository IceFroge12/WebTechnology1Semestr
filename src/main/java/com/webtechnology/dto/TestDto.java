package com.webtechnology.dto;

import com.webtechnology.model.Region;

import java.io.Serializable;

/**
 * Created by IO on 08.11.2016.
 */
public class TestDto implements Serializable {
    private Region region;
    private int amount;

    public TestDto() {
    }

    public TestDto(Region region) {
        this.region = region;
    }

    public TestDto(Region region, int amount) {
        this.region = region;
        this.amount = amount;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
