package gplx.xowa.bldrs.cmds.wikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*; import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.langs.htmls.encoders.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.parsers.utils.*;
public class Xob_redirect_cmd extends Xob_dump_mgr_base {		
	private Db_conn conn; private Xob_redirect_tbl redirect_tbl;
	private Xodb_mgr_sql db_mgr; private Xop_redirect_mgr redirect_mgr; private Gfo_url_encoder encoder;
	public Xob_redirect_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); this.Reset_db_y_();}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_wiki_redirect;}
	@Override public int[] Init_ns_ary() {return Int_.Ary(Xow_ns_.Tid__file);}	// restrict to file ns
	@Override public byte Init_redirect() {return Bool_.Y_byte;}					// restrict to redirects
	@Override protected void Init_reset(Db_conn conn) {
		Db_cfg_tbl cfg_tbl = new Db_cfg_tbl(conn, "xowa_cfg");
		cfg_tbl.Delete_all();
		conn.Exec_sql("DELETE FROM " + Xob_redirect_tbl.Tbl_name);
	}
	@Override protected Db_conn Init_db_file() {
		this.db_mgr = wiki.Db_mgr_as_sql();
		redirect_mgr = wiki.Redirect_mgr();
		encoder = gplx.langs.htmls.encoders.Gfo_url_encoder_.Http_url_ttl;
		redirect_tbl = new Xob_redirect_tbl(wiki.Fsys_mgr().Root_dir(), gplx.langs.htmls.encoders.Gfo_url_encoder_.Http_url_ttl).Create_table();
		conn = redirect_tbl.Conn();
		conn.Txn_bgn("bldr__redirect");
		return conn;
	}		
	@Override protected void Cmd_bgn_end() {}
	@Override public void Exec_pg_itm_hook(int ns_ord, Xow_ns ns, Xowd_page_itm page, byte[] page_src) {
		Xoa_ttl redirect_ttl = redirect_mgr.Extract_redirect(page_src);
		byte[] redirect_ttl_bry = Xoa_ttl.Replace_spaces(redirect_ttl.Page_db());	// NOTE: spaces can still exist b/c redirect is scraped from #REDIRECT which sometimes has a mix; EX: "A_b c"
		redirect_ttl_bry = encoder.Decode(redirect_ttl_bry);
		redirect_tbl.Insert(page.Id(), Xoa_ttl.Replace_spaces(page.Ttl_page_db()), -1, redirect_ttl.Ns().Id(), redirect_ttl_bry, redirect_ttl.Anch_txt(), 1);
	}
	@Override public void Exec_commit_hook() {
		conn.Txn_sav();
	}
	@Override public void Exec_end_hook() {
		conn.Txn_end();			
		redirect_tbl.Create_indexes(usr_dlg);
		redirect_tbl.Update_trg_redirect_id(db_mgr.Core_data_mgr().Db__core().Url(), 4);
	}
}
