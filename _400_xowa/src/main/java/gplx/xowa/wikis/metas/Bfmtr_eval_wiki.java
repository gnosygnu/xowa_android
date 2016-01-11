package gplx.xowa.wikis.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.gfs.*;
public class Bfmtr_eval_wiki implements Bry_fmtr_eval_mgr {
	public Bfmtr_eval_wiki(Xowe_wiki wiki) {this.wiki = wiki;} private Xowe_wiki wiki;
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled = true;
	public byte[] Eval(byte[] cmd) {
		Object rslt = GfsCore.Instance.Exec_bry(cmd, wiki);
		return Bry_.new_u8(Object_.Xto_str_strict_or_null_mark(rslt));
	}
	public void Eval_mgr_(Bry_fmtr... fmtrs) {
		int fmtrs_len = fmtrs.length;
		for (int i = 0; i < fmtrs_len; i++)
			fmtrs[i].Eval_mgr_(this);
	}
}
