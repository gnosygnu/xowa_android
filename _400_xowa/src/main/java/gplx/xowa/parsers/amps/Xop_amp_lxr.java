package gplx.xowa.parsers.amps; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public class Xop_amp_lxr implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_amp;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Byte_ascii.Amp, this);}
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		return ctx.Amp().Make_tkn(ctx, tkn_mkr, root, src, src_len, bgn_pos, cur_pos);
	}
	public static final Xop_amp_lxr Instance = new Xop_amp_lxr();
}
