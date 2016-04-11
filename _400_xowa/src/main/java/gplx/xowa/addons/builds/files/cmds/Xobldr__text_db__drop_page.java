package gplx.xowa.addons.builds.files.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.files.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.wikis.data.*; import gplx.dbs.*; import gplx.dbs.engines.sqlite.*; import gplx.xowa.wikis.dbs.*;
import gplx.xowa.addons.builds.files.dbs.*;
public class Xobldr__text_db__drop_page extends Xob_cmd__base {
	public Xobldr__text_db__drop_page(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		Xowd_db_mgr db_mgr = wiki.Data__core_mgr();
		int len = db_mgr.Dbs__len();
		for (int i = 0; i < len; i++) {
			Xowd_db_file db_file = db_mgr.Dbs__get_at(i);
			switch (db_file.Tid()) {
				case Xowd_db_file_.Tid_wiki_solo:
				case Xowd_db_file_.Tid_text_solo:
				case Xowd_db_file_.Tid_text:
					db_file.Conn().Meta_tbl_delete(Xob_page_dump_tbl.Tbl_name);
					break;
			}
		}
	}

	public static final String BLDR_CMD_KEY = "wiki.page_dump.drop";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xobldr__text_db__drop_page(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xobldr__text_db__drop_page(bldr, wiki);}
}
