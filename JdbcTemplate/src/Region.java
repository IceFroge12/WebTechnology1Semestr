import com.sun.org.apache.regexp.internal.RE;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IO on 11.11.2016.
 */
public class Region implements Serializable {

    private long id;
    private String title;

    private ResultSetExtractor<Region> extractor = resultSet -> {
        Region region = new Region();
        region.setId(resultSet.getLong("id"));
        region.setTitle(resultSet.getString("title"));
        return region;
    };

    public Region() {
    }

    public Region(long id, String title) {
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
