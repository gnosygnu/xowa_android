package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public class Xop_tab_lxr implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_tab;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Byte_ascii.Tab, this); core_trie.Add(Xop_tab_tkn.Bry_tab_ent, this);}	
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		cur_pos = Bry_find_.Find_fwd_while(src, cur_pos, src_len, Byte_ascii.Tab);
		src[bgn_pos] = Byte_ascii.Tab; // HACK: SEE:NOTE_1:tabs
		for (int i = bgn_pos + 1; i < cur_pos; i++)	
			src[i] = Byte_ascii.Space;
		ctx.Subs_add(root, tkn_mkr.Tab(bgn_pos, cur_pos));
		return cur_pos;
	}
	public static final Xop_tab_lxr Instance = new Xop_tab_lxr();
}
