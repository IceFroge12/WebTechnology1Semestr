package sqlbuilder;


/**
 * Created by IO on 07.12.2016.
 */
public class GroupBySql extends WhereSql {
    public WhereSql having(WhereSegment having) {
        sb.append(" HAVING ").append(having.sb);
        this.getParams().addAll(having.getParams());
        return this;
    }
}
