package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.criterias.*; import gplx.core.lists.*; /*GfoNde*/ import gplx.dbs.qrys.*; import gplx.core.gfo_ndes.*;
class TdbDeleteWkr implements Db_qryWkr {
	public Object Exec(Db_engine engineObj, Db_qry cmdObj) {
		TdbEngine engine = TdbEngine.cast(engineObj); Db_qry_delete cmd = (Db_qry_delete)cmdObj;
		TdbTable tbl = engine.FetchTbl(cmd.Base_table());
		List_adp deleted = List_adp_.new_();
		int rv = 0;
		if (cmd.Where() == Db_qry_delete.Where__null) {
			rv = tbl.Rows().Count();
			tbl.Rows().Clear();
		}
		else {
			Criteria crt = cmd.Where();
			for (int i = 0; i < tbl.Rows().Count(); i++) {
				GfoNde row = tbl.Rows().FetchAt_asGfoNde(i);
				if (crt.Matches(row))
					deleted.Add(row);
			}
			for (int i = 0; i < deleted.Count(); i++) {
				GfoNde row = (GfoNde)deleted.Get_at(i);
				tbl.Rows().Del(row);
				rv++;
			}
		}
		if (rv > 0) tbl.IsDirty_set(true);
		return rv;
	}
	public static TdbDeleteWkr new_() {TdbDeleteWkr rv = new TdbDeleteWkr(); return rv;}
}
