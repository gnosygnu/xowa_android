package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.wikis.data.*; import gplx.dbs.*; import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.data.tbls.*;
public class Xob_wdata_qid_sql extends Xob_wdata_qid_base {
	private Xowd_wbase_qid_tbl tbl;
	@Override public String Wkr_key() {return gplx.xowa.bldrs.Xob_cmd_keys.Key_wbase_qid;}
	@Override public void Qid_bgn() {
		Xowd_db_mgr db_mgr = wiki.Db_mgr_as_sql().Core_data_mgr();
		boolean db_is_all_or_few = db_mgr.Props().Layout_text().Tid_is_all_or_few();
		Xowd_db_file wbase_db = db_is_all_or_few
			? db_mgr.Db__core()
			: db_mgr.Dbs__make_by_tid(Xowd_db_file_.Tid_wbase);
		if (db_is_all_or_few)
			db_mgr.Db__wbase_(wbase_db);
		tbl = wbase_db.Tbl__wbase_qid();
		tbl.Create_tbl();
		tbl.Insert_bgn();
	}
	@Override public void Qid_add(byte[] wiki_key, int ns_id, byte[] ttl, byte[] qid) {
		tbl.Insert_cmd_by_batch(wiki_key, ns_id, ttl, qid);
	}
	@Override public void Qid_end() {
		tbl.Insert_end();
		tbl.Create_idx();
	}
}
