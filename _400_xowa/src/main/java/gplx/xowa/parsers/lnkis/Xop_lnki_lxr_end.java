package gplx.xowa.parsers.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
import gplx.xowa.parsers.tmpls.*;
public class Xop_lnki_lxr_end implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_lnki_end;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Xop_tkn_.Lnki_end, this);}
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {return ctx.Lnki().Make_tkn(ctx, tkn_mkr, root, src, src_len, bgn_pos, cur_pos);}
	public static final Xop_lnki_lxr_end Instance = new Xop_lnki_lxr_end();
}
