package com.webtechnology.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IO on 01.11.2016.
 */
@SqlResultSetMapping(name = "StyleFilterMapping",
        classes = {
                @ConstructorResult(targetClass = Style.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "amount", type = Integer.class)
                        })
        })
@NamedNativeQueries({
        @NamedNativeQuery(name = "Style.getValuesForFilter",
                query = "select s.id, s.title, count(trip.id) as amount  " +
                        "from public.style s " +
                        "inner join Trip trip " +
                        "on s.id = trip.id_style " +
                        "group by s.id",
                resultSetMapping = "StyleFilterMapping"),
        @NamedNativeQuery(name = "Style.getFilteredValuesForFilter",
                query = "SELECT s.id, s.title, count(t.id) as amount " +
                        "FROM style s " +
                        "INNER JOIN trip t " +
                        "on s.id = t.id_style " +
                        "WHERE (t.id_region  = ANY(:regions)) and (t.id_theme = ANY(:themes)) " +
                        "GROUP BY s.id",
                resultSetMapping = "StyleFilterMapping"
        ),
})
@Entity
public class Style implements Serializable {

    @Id
    @SequenceGenerator(name="styleSequence", sequenceName="style_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "styleSequence")
    private long id;
    private String title;

    @Transient
    private long amount;


    public Style() {
    }

    public Style(String title) {
        this.title = title;
    }

    public Style(long id, String title, long amount) {
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
        return "Style{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }
}
