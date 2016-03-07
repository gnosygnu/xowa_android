package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
public interface Xob_page_wkr extends GfoInvkAble {
	String Wkr_key();
	void Wkr_bgn(Xob_bldr bldr);
	void Wkr_run(gplx.xowa.wikis.data.tbls.Xowd_page_itm page);
	void Wkr_end();
}
