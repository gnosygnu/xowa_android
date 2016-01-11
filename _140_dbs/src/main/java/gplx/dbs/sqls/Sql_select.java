package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.strings.*;
import gplx.dbs.engines.tdbs.*;
public class Sql_select {
	public Sql_select_fld_list Flds() {return flds;} Sql_select_fld_list flds = Sql_select_fld_list.new_();
	public boolean Distinct() {return distinct;} public void Distinct_set(boolean v) {distinct = v;} private boolean distinct;
	public void Add(String fldName) {flds.Add(Sql_select_fld_.new_fld(Sql_select_fld_base.Tbl_null, fldName, fldName));}
	public void Add(String fldName, String alias) {flds.Add(Sql_select_fld_.new_fld(Sql_select_fld_base.Tbl_null, fldName, alias));}
	public void Add(Sql_select_fld_base fld) {flds.Add(fld);}

	public static final Sql_select All = all_(); static Sql_select all_() {Sql_select rv = new_(); rv.Add(Sql_select_fld_wild.Instance); return rv;}
	public static Sql_select new_() {return new Sql_select();} Sql_select() {}
}
