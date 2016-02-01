package gplx.xowa.bldrs.cmds.wikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.xowa.wikis.data.*; import gplx.dbs.*; import gplx.dbs.engines.sqlite.*; import gplx.xowa.wikis.dbs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Xob_page_dump_cmd_make extends Xob_itm_basic_base implements Xob_cmd {
	public Xob_page_dump_cmd_make(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Xob_cmd_keys.Key_wiki_page_dump_make;}
	public void Cmd_run() {
		wiki.Init_assert();
		Xowd_db_mgr db_mgr = wiki.Data__core_mgr();
		Io_url page_db_url = db_mgr.Db__core().Url();
		int len = db_mgr.Dbs__len();
		for (int i = 0; i < len; i++) {
			Xowd_db_file db_file = db_mgr.Dbs__get_at(i);
			switch (db_file.Tid()) {
				case Xowd_db_file_.Tid_wiki_solo:
				case Xowd_db_file_.Tid_text_solo:
				case Xowd_db_file_.Tid_text:
					Xobd_page_dump_tbl tbl = new Xobd_page_dump_tbl(db_file.Conn());
					tbl.Create_data(page_db_url, db_file.Id());
					break;
			}
		}
	}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_end() {}
	public void Cmd_term() {}
}