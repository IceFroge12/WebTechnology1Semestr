package sqlbuilder;

/**
 * Created by IO on 07.12.2016.
 */
public class UpdateSetSegment extends UpdateSql{

	public UpdateSetSegment set(SqlColumn column, Object value){
		UpdateSetSegment up = new UpdateSetSegment();
		up.sb = this.sb;
		up.params = this.params;
		
		up.sb.append(", ");
		sb.append(column.sb).append(" =  ").append("'").append(value).append("'");
		
		up.params.add(value);
		return up;
	}
	
	public UpdateSql where(WhereSegment conj){
		this.sb.append(" WHERE ").append(conj.toString());
		this.params.addAll(conj.params);
		return this;
	}
}
