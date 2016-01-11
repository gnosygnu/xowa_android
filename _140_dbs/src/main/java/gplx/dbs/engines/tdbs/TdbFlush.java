package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.lists.*; /*GfoNde*/ import gplx.dbs.qrys.*;
class TdbFlushWkr implements Db_qryWkr {
	public Object Exec(Db_engine engineObj, Db_qry cmdObj) {
		TdbEngine engine = TdbEngine.cast(engineObj); Db_qry_flush cmd = Db_qry_flush.cast(cmdObj);
		if (Array_.Len(cmd.TableNames()) == 0)
			engine.FlushAll();
		else {
			for (String tblName : cmd.TableNames()) {
				TdbTable tbl = engine.FetchTbl(tblName);
				if (tbl.IsDirty()) engine.FlushTbl(tbl);
			}
		}
		return 1;
	}
	public static TdbFlushWkr new_() {TdbFlushWkr rv = new TdbFlushWkr(); return rv;}
}
