package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.ios.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.texts.tdbs.*;
class Wdata_idx_wtr {
	public Wdata_idx_wtr(Io_url dump_dir, int dump_fil_max, Io_url make_dir) {
		this.dump_dir = dump_dir;
		this.dump_fil_max = dump_fil_max;
		dump_url_gen = Io_url_gen_.dir_(dump_dir);
		this.make_dir = make_dir;
	}	private Bry_bfr dump_bfr = Bry_bfr_.Reset(2 * Io_mgr.Len_kb); int dump_fil_max; Io_url dump_dir, make_dir; Io_url_gen dump_url_gen;
	public void Write(byte[] ttl, byte[] qid) {
		if (dump_bfr.Len() + ttl.length + qid.length + 2 > dump_fil_max) Flush();	// 2 = "|" + "\n"; NOTE: all items have format of "data|qid\n"
		dump_bfr.Add(ttl).Add_byte_pipe().Add(qid).Add_byte_nl();
	}
	public void Flush() {
		Io_mgr.Instance.AppendFilBfr(dump_url_gen.Nxt_url(), dump_bfr);
	}
	public void Make(Gfo_usr_dlg usr_dlg, int make_fil_len) {
		Xobdc_merger.Basic(usr_dlg, dump_url_gen, dump_dir.OwnerDir().GenSubDir("sort"), dump_fil_max, Io_line_rdr_key_gen_.first_pipe, new Xob_make_cmd_site(usr_dlg, make_dir, make_fil_len));
	}
	public static Wdata_idx_wtr new_qid_(Xowe_wiki wdata_wiki, String wiki_str, String ns_num, int dump_fil_max) {
		Io_url dump_dir = wdata_wiki.Fsys_mgr().Tmp_dir().GenSubDir_nest("wdata.qid", "qid", wiki_str, ns_num, "dump");	// /xowa/wiki/www.wikidata.org/tmp/wdata_qid/ + enwiki/000/dump/
		Io_url make_dir = dir_qid_(wdata_wiki, wiki_str, ns_num);																	// /xowa/wiki/www.wikidata.org/site/data/qid/ + enwiki/000/
		return new Wdata_idx_wtr(dump_dir, dump_fil_max, make_dir);
	}
	public static Wdata_idx_wtr new_pid_(Xowe_wiki wdata_wiki, String lang_key, int dump_fil_max) {
		Io_url dump_dir = wdata_wiki.Fsys_mgr().Tmp_dir().GenSubDir_nest("wdata.pid", "pid", lang_key, "dump");			// /xowa/wiki/www.wikidata.org/tmp/wdata_pid/ + en/
		Io_url make_dir = dir_pid_(wdata_wiki, lang_key);																			// /xowa/wiki/www.wikidata.org/site/data/pid/ + en/
		return new Wdata_idx_wtr(dump_dir, dump_fil_max, make_dir);
	}
	public static Io_url dir_qid_(Xowe_wiki wiki, String wiki_str, String ns_num) {
		return wiki.Tdb_fsys_mgr().Site_dir().GenSubDir_nest("data", "qid", wiki_str, ns_num);										// /xowa/wiki/www.wikidata.org/site/data/ + qid/enwiki/000/		
	}
	public static Io_url dir_pid_(Xowe_wiki wiki, String lang_key) {
		return wiki.Tdb_fsys_mgr().Site_dir().GenSubDir_nest("data", "pid", lang_key);												// /xowa/wiki/www.wikidata.org/site/data/ + pid/en/
	}
}
