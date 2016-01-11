package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*; import gplx.xowa.xtns.wdatas.core.*; import gplx.xowa.xtns.wdatas.parsers.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public abstract class Xob_wdata_pid_base extends Xob_itm_dump_base implements Xobd_wkr, GfoInvkAble {
	private Json_parser parser;
	public Xob_wdata_pid_base Ctor(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); return this;}
	public abstract String Wkr_key();
	public abstract void Pid_bgn();
	public abstract void Pid_add(byte[] src_lang, byte[] src_ttl, byte[] trg_ttl);
	public abstract void Pid_end();
	public void Wkr_ini(Xob_bldr bldr) {}
	public void Wkr_bgn(Xob_bldr bldr) {
		this.Init_dump(this.Wkr_key(), wiki.Tdb_fsys_mgr().Site_dir().GenSubDir_nest("data", "pid"));	// NOTE: must pass in correct make_dir in order to delete earlier version (else make_dirs will append)
		parser = bldr.App().Wiki_mgr().Wdata_mgr().Jdoc_parser();
		this.Pid_bgn();
	}
	public void Wkr_run(Xowd_page_itm page) {
		if (page.Ns_id() != Wdata_wiki_mgr.Ns_property) return;
		Json_doc jdoc = parser.Parse(page.Text()); 
		if (jdoc == null) {
			bldr.Usr_dlg().Warn_many(GRP_KEY, "json.invalid", "json is invalid: ns=~{0} id=~{1}", page.Ns_id(), String_.new_u8(page.Ttl_page_db()));
			return;
		}
		Parse_jdoc(jdoc);
	}
	public void Parse_jdoc(Json_doc jdoc) {
		Wdata_doc_parser wdoc_parser = app.Wiki_mgr().Wdata_mgr().Wdoc_parser(jdoc);
		byte[] qid = wdoc_parser.Parse_qid(jdoc);
		Ordered_hash list = wdoc_parser.Parse_langvals(qid, jdoc, Bool_.Y);
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Wdata_langtext_itm label = (Wdata_langtext_itm)list.Get_at(i);
			this.Pid_add(label.Lang(), label.Text(), qid);
		}
	}
	public void Wkr_end() {this.Pid_end();}
	public void Wkr_print() {}
	static final String GRP_KEY = "xowa.wdata.pid_wkr";
}
