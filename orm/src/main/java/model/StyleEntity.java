package model;

import annotation.Column;
import annotation.Entity;
import annotation.Id;

/**
 * Created by IO on 25.12.2016.
 */


@Entity(table = "style")
public class StyleEntity {

    @Column
    @Id
    private int id;

    @Column
    private String title;

    public StyleEntity() {
    }

    public StyleEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
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

}
