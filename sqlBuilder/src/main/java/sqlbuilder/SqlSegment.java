package sqlbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IO on 07.12.2016.
 */
public abstract class SqlSegment {
	protected StringBuilder sb = new StringBuilder();
	protected List<Object> params = new ArrayList<Object>();

	@Override
	public String toString() {
		return sb.toString();
	}

	protected SqlSegment() {
		super();
	}
	
	public String getSQL(){
		return sb.toString();
	}
	
}
