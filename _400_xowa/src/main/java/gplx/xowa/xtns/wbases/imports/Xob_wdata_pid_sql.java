package gplx.xowa.xtns.wbases.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.wikis.data.*; import gplx.dbs.*; import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.data.tbls.*;
public class Xob_wdata_pid_sql extends Xob_wdata_pid_base {
	private Xowd_wbase_pid_tbl tbl;
	@Override public String Page_wkr__key() {return gplx.xowa.bldrs.Xob_cmd_keys.Key_wbase_pid;}
	@Override public void Pid_bgn() {
		Xow_db_mgr db_mgr = wiki.Data__core_mgr();
		tbl = db_mgr.Db__wbase().Tbl__wbase_pid();
		tbl.Create_tbl();
		tbl.Insert_bgn();
	}
	@Override public void Pid_add(byte[] lang_key, byte[] ttl, byte[] pid) {
		tbl.Insert_cmd_by_batch(lang_key, ttl, pid);
	}
	@Override public void Pid_end() {
		tbl.Insert_end();
		tbl.Create_idx();
	}
}
