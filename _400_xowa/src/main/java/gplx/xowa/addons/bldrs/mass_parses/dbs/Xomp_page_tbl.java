package gplx.xowa.addons.bldrs.mass_parses.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
public class Xomp_page_tbl implements Db_tbl {
	// private final    String fld_page_id, fld_page_status, fld_page_mgr_id;
	private final    Db_conn conn;
	public Xomp_page_tbl(Db_conn conn) {
		this.conn = conn;
		this.tbl_name				= "xomp_page";
		flds.Add_int_pkey("xomp_uid");
		flds.Add_int("page_id");
		flds.Add_int("page_ns");
		flds.Add_byte("page_status");			// 0=wait; 1=done; 2=fail
		flds.Add_int_dflt("html_len", -1);
		flds.Add_int_dflt("xomp_wkr_id", -1);
		conn.Rls_reg(this);
	}
	public String Tbl_name() {return tbl_name;} private final    String tbl_name;
	public Dbmeta_fld_list Flds() {return flds;} private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	public void Create_tbl() {
		conn.Meta_tbl_create
		( Dbmeta_tbl_itm.New(tbl_name, flds
		, Dbmeta_idx_itm.new_normal_by_tbl("xomp_page", "xomp_uid__page_status"	, "xomp_uid", "page_status")// for parse
		, Dbmeta_idx_itm.new_normal_by_tbl("xomp_page", "page_ns__page_id"		, "page_ns", "page_id")		// for make
		));
	}
	public void Rls() {}
}
