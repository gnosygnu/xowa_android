package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public class Xop_brack_bgn_lxr implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_brack_bgn;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Xop_tkn_.Lnki_bgn, this);}
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		Xop_tkn_itm tkn = tkn_mkr.Brack_bgn(bgn_pos, cur_pos);
		ctx.Subs_add_and_stack(root, tkn);			
		return cur_pos;
	}
	public static final Xop_brack_bgn_lxr Instance = new Xop_brack_bgn_lxr(); Xop_brack_bgn_lxr() {}
}
