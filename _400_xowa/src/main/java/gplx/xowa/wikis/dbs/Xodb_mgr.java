package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.*; import gplx.xowa.htmls.core.*;
public interface Xodb_mgr extends Gfo_invk {
	byte Tid();
	String Tid_name();
	byte Category_version();
	Xodb_load_mgr Load_mgr();
	Xodb_save_mgr Save_mgr();
	DateAdp Dump_date_query(); // used by maint_mgr
}
