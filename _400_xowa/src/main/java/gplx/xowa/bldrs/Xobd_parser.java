package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.core.btries.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xobd_parser implements Xob_page_wkr {
	private final    Xob_bldr bldr;
	private final    Btrie_slim_mgr trie = Btrie_slim_mgr.ci_a7();		// NOTE:ci.ascii:MW_const.en; ctg.v1 assumes [[Category:
	private final    List_adp wkr_list = List_adp_.New();
	public String Page_wkr__key() {return KEY;} static final String KEY = "page_parser";
	public Xobd_parser(Xob_bldr bldr) {this.bldr = bldr;}
	public void Wkr_add(Xobd_parser_wkr wkr) {wkr_list.Add(wkr);}
	public void Page_wkr__bgn() {
		int wkr_list_len = wkr_list.Count();
		for (int i = 0; i < wkr_list_len; i++) {
			Xobd_parser_wkr wkr = (Xobd_parser_wkr)wkr_list.Get_at(i);
			wkr.Wkr_bgn(bldr);
			int hooks_len = wkr.Wkr_hooks().Count();
			for (int j = 0; j < hooks_len; j++) {
				byte[] bry = (byte[])wkr.Wkr_hooks().Get_at(j);
				trie.Add_obj(bry, wkr);
			}
		}
	}
	public void Page_wkr__run(Xowd_page_itm page) {
		byte[] src = page.Text(); int src_len = src.length;
		int pos = 0;
		while (true) {
			if (pos == src_len) break;
			Object o = trie.Match_bgn(src, pos, src_len);
			if (o == null)
				++pos;
			else {
				Xobd_parser_wkr wkr = (Xobd_parser_wkr)o;
				pos = wkr.Wkr_run(page, src, src_len, pos, trie.Match_pos());
			}
		}
	}
	public void Page_wkr__run_cleanup() {}
	public void Page_wkr__end() {
		int wkr_list_len = wkr_list.Count();
		for (int i = 0; i < wkr_list_len; i++) {
			Xobd_parser_wkr wkr = (Xobd_parser_wkr)wkr_list.Get_at(i);
			wkr.Wkr_end();
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		throw Err_.new_unimplemented();
	}
}
