package gplx.xowa.addons.bldrs.exports.splits.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.dbs.*;
public interface Split_rslt_wkr {
	byte Tid();
	int Row_count();
	long Obj_size();
	void On__init(Split_rslt_mgr rslt_mgr, Db_conn wkr_conn);
	void On__nth__new(int db_id);
	void On__nth__rls();
	void On_term();
}
