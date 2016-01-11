package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.gfo_ndes.*; import gplx.core.type_xtns.*;
public abstract class Sql_select_fld_base {
	public String Tbl() {return tbl;} public void Tbl_set(String val) {tbl = val;} private String tbl;
	public String Fld() {return fld;} public void Fld_set(String val) {fld = val;} private String fld;
	public String Alias() {return alias;} public void Alias_set(String val) {alias = val;} private String alias;
	public ClassXtn ValType() {return valType;} public void ValType_set(ClassXtn val) {valType = val;} ClassXtn valType = ObjectClassXtn.Instance;
	public abstract Object GroupBy_eval(Object groupByVal, Object curVal, ClassXtn type);
	@gplx.Virtual public void GroupBy_type(GfoFld fld) {this.ValType_set(fld.Type());}
	@gplx.Virtual public boolean Type_fld() {return true;}
	public abstract String XtoSql();
	public static final String Tbl_null = null;
	@gplx.Internal protected void ctor_(String tbl, String fld, String alias) {
		Tbl_set(tbl); Fld_set(fld); Alias_set(alias);
	}
}
class Sql_select_fld_wild extends Sql_select_fld_base {
	@Override public Object GroupBy_eval(Object groupByVal, Object curVal, ClassXtn type) {throw Err_.new_wo_type("group by eval not allowed on *");}
	@Override public void GroupBy_type(GfoFld fld) {throw Err_.new_wo_type("group by type not allowed on *");}
	@Override public String XtoSql() {return "*";}
	public static final Sql_select_fld_wild Instance = new Sql_select_fld_wild(); Sql_select_fld_wild() {this.ctor_(Tbl_null, "*", "*");}
}
abstract class Sql_select_fld_func_base extends Sql_select_fld_base {
	public abstract String XtoSql_functionName();
	@Override public boolean Type_fld() {return false;}
	@Override public String XtoSql() {
		return String_.Format("{0}({1}) AS {2}", XtoSql_functionName(), Fld(), Alias());
	}
}
