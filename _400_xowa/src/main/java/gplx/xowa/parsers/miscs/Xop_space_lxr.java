package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public class Xop_space_lxr implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_space;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Byte_ascii.Space, this);}
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		cur_pos = Bry_find_.Find_fwd_while(src, cur_pos, src_len, Byte_ascii.Space);
		ctx.Subs_add(root, tkn_mkr.Space(root, bgn_pos, cur_pos));
		return cur_pos;
	}
	public static final Xop_space_lxr Instance = new Xop_space_lxr();
}
