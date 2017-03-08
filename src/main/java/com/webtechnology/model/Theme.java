package com.webtechnology.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IO on 01.11.2016.
 */
@SqlResultSetMapping(name = "ThemeFilterMapping",
        classes = {
                @ConstructorResult(targetClass = Theme.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "amount", type = Integer.class)
                        })
        })

@NamedNativeQueries({
        @NamedNativeQuery(name = "Theme.getValuesForFilter",
                query = "select t.id, t.title, count(trip.id) as amount  " +
                        "from public.theme t " +
                        "inner join Trip trip " +
                        "on t.id = trip.id_theme " +
                        "group by t.id",
                resultSetMapping = "ThemeFilterMapping"),
        @NamedNativeQuery(name = "Theme.getFilteredValuesForFilter",
                query = "SELECT th.id, th.title, count(t.id) as amount FROM theme th INNER JOIN trip t on th.id = t.id_theme " +
                        "WHERE" +
                        " t.id_region in :regions GROUP BY s.id",
                resultSetMapping = "StyleFilterMapping"
        ),
})

@Entity
public class Theme implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;

    @Transient
    private long amount;

    public Theme() {
    }

    public Theme(long id, String title, long amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }
}
