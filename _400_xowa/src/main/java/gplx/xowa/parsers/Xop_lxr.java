package gplx.xowa.parsers; import gplx.*; import gplx.xowa.*;
import gplx.core.btries.*; import gplx.xowa.langs.*;
public interface Xop_lxr {
	int		Lxr_tid();
	void	Init_by_wiki(Xowe_wiki wiki, Btrie_fast_mgr core_trie);
	void	Init_by_lang(Xol_lang_itm lang, Btrie_fast_mgr core_trie);
	void	Term(Btrie_fast_mgr core_trie);
	int		Make_tkn(Xop_ctx ctx, Xop_tkn_mkr tkn_mkr, Xop_root_tkn root, byte[] src, int src_len, int bgn_pos, int cur_pos);
}
