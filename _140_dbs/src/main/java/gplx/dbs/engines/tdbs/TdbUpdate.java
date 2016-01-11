package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.criterias.*; import gplx.core.lists.*; /*GfoNde*/ import gplx.core.gfo_ndes.*;
import gplx.dbs.qrys.*;
class TdbUpdateWkr implements Db_qryWkr {
	public Object Exec(Db_engine engineObj, Db_qry cmdObj) {
		TdbEngine engine = TdbEngine.cast(engineObj); Db_qry_update cmd = (Db_qry_update)cmdObj;

		int rv = 0;
		TdbTable tbl = engine.FetchTbl(cmd.Base_table());
		Criteria crt = cmd.Where();
		for (int i = 0; i < tbl.Rows().Count(); i++) {
			GfoNde row = (GfoNde)tbl.Rows().FetchAt_asGfoNde(i);
			if (crt.Matches(row)) {
				UpdateRow(cmd, row);
				rv++;
			}
		}
		if (rv > 0) tbl.IsDirty_set(true);
		return rv;
	}
	void UpdateRow(Db_qry_update cmd, GfoNde row) {
		for (int i = 0; i < cmd.Args().Count(); i++) {
			KeyVal p = (KeyVal)cmd.Args().Get_at(i);
			Db_arg prm = (Db_arg)p.Val();
			row.Write(p.Key(), prm.Val());
		}
	}
	public static TdbUpdateWkr new_() {TdbUpdateWkr rv = new TdbUpdateWkr(); return rv;}
}
