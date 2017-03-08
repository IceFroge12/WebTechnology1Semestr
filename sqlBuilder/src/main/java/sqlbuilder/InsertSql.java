package sqlbuilder;

import java.util.List;

/**
 * Created by IO on 26.12.2016.
 */
public class InsertSql extends SqlSegment{
    public List<Object> getParams(){
        return this.params;
    }
}
