package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public interface Xob_page_wkr extends Gfo_invk {
	String	Page_wkr__key();
	void	Page_wkr__bgn();
	void	Page_wkr__run(gplx.xowa.wikis.data.tbls.Xowd_page_itm page);
	void	Page_wkr__run_cleanup();	// close txns opened during Page_wkr__run
	void	Page_wkr__end();
}
