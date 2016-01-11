package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.xowa.wikis.data.tbls.*;
interface Xowd_page_cmd {
	String Cmd_key();
	void Cmd_bgn(Xob_bldr bldr);
	void Cmd_run(Xowd_page_itm page);
	void Cmd_end();
}
