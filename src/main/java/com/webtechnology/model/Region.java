package com.webtechnology.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IO on 27.10.2016.
 */
@SqlResultSetMapping(name = "RegionFilterMapping",
        classes = {
                @ConstructorResult(targetClass = Region.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "amount", type = Integer.class)
                        })
        })

@NamedNativeQuery(name = "Region.getValuesForFilter",
        query = "select r.id, r.title, count(trip.id) as amount  " +
                "from public.region r " +
                "inner join Trip trip " +
                "on r.id = trip.id_region " +
                "group by r.id",
        resultSetMapping = "RegionFilterMapping")

@Entity
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @Transient
    private long amount;


    public Region() {
    }

    public Region(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Region(long id, String title, long amount) {
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }
}
