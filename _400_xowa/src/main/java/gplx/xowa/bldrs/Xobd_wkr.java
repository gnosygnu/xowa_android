package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.xowa.wikis.data.tbls.*;
public interface Xobd_wkr extends GfoInvkAble {
	String Wkr_key();
	void Wkr_ini(Xob_bldr bldr);
	void Wkr_bgn(Xob_bldr bldr);
	void Wkr_run(Xowd_page_itm page);
	void Wkr_end();
	void Wkr_print();
}
