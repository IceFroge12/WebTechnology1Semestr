package sqlbuilder;

/**
 * Created by IO on 07.12.2016.
 */
public class DeleteWhereSegment extends DeleteSql{

    public DeleteSql where(WhereSegment conj){
        this.sb.append(" WHERE ").append(conj.toString());
        this.params.addAll(conj.params);
        return this;
    }
}
