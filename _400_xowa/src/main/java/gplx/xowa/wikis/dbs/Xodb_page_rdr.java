package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.wikis.data.tbls.*;
public interface Xodb_page_rdr {
	boolean Move_next();
	boolean Read(Xowd_page_itm page);
	void Rls();
}
