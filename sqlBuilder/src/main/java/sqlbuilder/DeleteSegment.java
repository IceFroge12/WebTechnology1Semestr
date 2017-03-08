package sqlbuilder;

/**
 * Created by IO on 07.12.2016.
 */
public class DeleteSegment extends DeleteSql{

    public DeleteWhereSegment from(SqlTable table) {
            sb.append(" FROM ").append(table.getName());

        DeleteWhereSegment s = new DeleteWhereSegment();
        s.sb = sb;
        s.params = this.params;
        return s;
    }
}
