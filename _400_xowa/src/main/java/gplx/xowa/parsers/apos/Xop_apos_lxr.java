package gplx.xowa.parsers.apos; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public class Xop_apos_lxr implements Xop_lxr {
	public int Lxr_tid() {return Xop_lxr_.Tid_apos;}
	public void Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie) {core_trie.Add(Apos_ary, this);} private static final byte[] Apos_ary = new byte[] {Byte_ascii.Apos, Byte_ascii.Apos};
	public void Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie) {}
	public void Term(Btrie_fast_mgr core_trie) {}
	public int Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos) {return ctx.Apos().Make_tkn(ctx, tkn_mkr, root, src, src_len, bgn_pos, cur_pos);}
	public static final Xop_apos_lxr Instance = new Xop_apos_lxr(); Xop_apos_lxr() {}
}
