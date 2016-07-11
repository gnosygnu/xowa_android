package gplx.xowa.htmls.core.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.htmls.core.dbs.*;
public interface Xob_hdump_tbl_retriever {
	Xowd_html_tbl Get_html_tbl(Xow_ns ns, int prv_row_len);
	void Commit();
	void Rls_all();
}
