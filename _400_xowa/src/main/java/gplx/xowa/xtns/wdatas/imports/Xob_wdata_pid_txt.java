package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xob_wdata_pid_txt extends Xob_wdata_pid_base {
	@Override public String Page_wkr__key() {return gplx.xowa.bldrs.Xob_cmd_keys.Key_tdb_text_wdata_pid;}
	@Override public void Pid_bgn() {
		pid_bldr = new Wdata_idx_bldr_pid(this, bldr, wiki, dump_fil_len);
	}	Wdata_idx_bldr_pid pid_bldr;
	@Override public void Pid_add(byte[] lang_key, byte[] prop_key, byte[] qid) {
		pid_bldr.Add(lang_key, prop_key, qid);
	}
	@Override public void Pid_end() {
		pid_bldr.Flush();
		pid_bldr.Make();
	}
}
class Wdata_idx_bldr_pid extends Wdata_idx_mgr_base {
	public Wdata_idx_bldr_pid(Xob_itm_dump_base wkr, Xob_bldr bldr, Xowe_wiki wiki, int dump_fil_len) {this.Ctor(wkr, bldr, wiki, dump_fil_len);}
	public void Add(byte[] lang, byte[] prop_key, byte[] pid) {
		Wdata_idx_wtr wtr = Get_or_new(lang);
		wtr.Write(prop_key, pid);
	}
	public Wdata_idx_wtr Get_or_new(byte[] lang_bry) {
		String lang = String_.Lower(String_.new_u8(lang_bry));	// NOTE: for some reason, both "en" and "En" can be added; normalize case
		Object rv = hash.Get_by(lang);
		if (rv == null) {
			Wdata_idx_wtr wtr = Wdata_idx_wtr.new_pid_(wiki, lang, dump_fil_len);
			hash.Add(lang, wtr);
			return wtr;
		}
		return (Wdata_idx_wtr)rv;
	}
}
