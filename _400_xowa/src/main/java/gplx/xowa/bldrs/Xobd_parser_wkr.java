package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.xowa.wikis.data.tbls.*;
public interface Xobd_parser_wkr extends Gfo_invk {
	Ordered_hash Wkr_hooks();
	void Wkr_bgn(Xob_bldr bldr);
	int Wkr_run(Xowd_page_itm page, byte[] src, int src_len, int bgn, int end);
	void Wkr_end();
}
