package gplx.xowa.addons.wikis.ctgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.wikis.dbs.*;
public class Xodb_cat_db_ {
	public static Xowd_cat_core_tbl Get_cat_core_or_fail(Xow_db_mgr db_mgr) {
		Xow_db_file cat_core_db = db_mgr.Dbs__get_by_tid_or_core(Xow_db_file_.Tid__cat_core);
		Xowd_cat_core_tbl cat_core_tbl = new Xowd_cat_core_tbl(cat_core_db.Conn(), db_mgr.Props().Schema_is_1());
		return cat_core_tbl;
	}
}
