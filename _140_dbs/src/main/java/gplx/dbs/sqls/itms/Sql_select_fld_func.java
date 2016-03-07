package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
import gplx.core.type_xtns.*;
abstract class Sql_select_fld_func extends Sql_select_fld {	//#*inherit
	public Sql_select_fld_func(String tbl, String fld, String alias) {super(tbl, fld, alias);}
	public abstract String XtoSql_functionName();
	@Override public String To_fld_sql() {
		return String_.Format("{0}({1}) AS {2}", XtoSql_functionName(), Fld, Alias);
	}
}
class Sql_select_fld_count extends Sql_select_fld_func {		//#*inherit
	public Sql_select_fld_count(String tbl, String fld, String alias) {super(tbl, fld, alias);}
	@Override public String XtoSql_functionName() {return "COUNT";}
	@Override public void GroupBy_type(ClassXtn type) {this.Val_type_(IntClassXtn.Instance);}
	@Override public Object GroupBy_eval(Object groupByVal, Object curVal, ClassXtn type) {
		if (groupByVal == null) return 1;
		return Int_.cast(groupByVal) + 1;
	}
}
class Sql_select_fld_sum extends Sql_select_fld_func {		//#*inherit
	public Sql_select_fld_sum(String tbl, String fld, String alias) {super(tbl, fld, alias);}
	@Override public String XtoSql_functionName() {return "SUM";}
	@Override public void GroupBy_type(ClassXtn type) {this.Val_type_(IntClassXtn.Instance);}
	@Override public Object GroupBy_eval(Object groupByVal, Object curVal, ClassXtn type) {
		if (groupByVal == null) return Int_.cast(curVal);
		return Int_.cast(groupByVal) + Int_.cast(curVal);
	}
}
class Sql_select_fld_minMax extends Sql_select_fld_func {	//#*inherit
	private final int compareType;
	public Sql_select_fld_minMax(int compareType, String tbl, String fld, String alias) {super(tbl, fld, alias);
		this.compareType = compareType;
	}
	@Override public String XtoSql_functionName() {return compareType == CompareAble_.Less ? "MIN" : "MAX";}
	@Override public Object GroupBy_eval(Object groupByVal, Object curVal, ClassXtn type) {
		if (groupByVal == null) return curVal;
		int compareVal = CompareAble_.Compare_obj(curVal, groupByVal);
		return compareVal * compareType > 0 ? curVal : groupByVal;
	}
}
