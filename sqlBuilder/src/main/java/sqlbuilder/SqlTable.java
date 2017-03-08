package sqlbuilder;

/**
 * Created by IO on 07.12.2016.
 */
public class SqlTable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static SqlTable of(String tableName){
		SqlTable table= new SqlTable();
		table.name = tableName;
		return table;
	}

	private SqlTable() {
		super();
	}

}
