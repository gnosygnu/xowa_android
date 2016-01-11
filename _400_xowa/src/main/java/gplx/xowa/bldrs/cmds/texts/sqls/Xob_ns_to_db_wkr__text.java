package gplx.xowa.bldrs.cmds.texts.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
public class Xob_ns_to_db_wkr__text implements Xob_ns_to_db_wkr {
	public byte Db_tid() {return Xowd_db_file_.Tid_text;}
	public void Tbl_init(Xowd_db_file db) {
		Xowd_text_tbl tbl = db.Tbl__text();
		tbl.Create_tbl();
		tbl.Insert_bgn();
	}
	public void Tbl_term(Xowd_db_file db) {
		db.Tbl__text().Insert_end(); 
	}
}
