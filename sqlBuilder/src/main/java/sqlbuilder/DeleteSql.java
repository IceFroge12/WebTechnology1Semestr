package sqlbuilder;

import java.util.List;

/**
 * Created by IO on 07.12.2016.
 */
public class DeleteSql extends SqlSegment {
    public List<Object> getParams() {
        return this.params;
    }
}
