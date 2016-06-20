package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.xowa.wikis.data.*;
public interface Xob_ns_to_db_wkr {
	byte Db_tid();
	void Tbl_init(Xow_db_file db);
	void Tbl_term(Xow_db_file db);
}
