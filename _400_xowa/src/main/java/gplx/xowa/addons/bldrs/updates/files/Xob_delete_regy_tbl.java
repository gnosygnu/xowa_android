package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.dbs.*;
public class Xob_delete_regy_tbl {
	public final    String tbl_name = "delete_regy";
	public final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	public final    String fld_fil_id, fld_thm_id, fld_reason;
	public final    Db_conn conn;
	public Xob_delete_regy_tbl(Db_conn conn) {
		this.conn = conn;
		this.fld_fil_id			= flds.Add_int("fil_id");
		this.fld_thm_id			= flds.Add_int("thm_id");
		this.fld_reason			= flds.Add_str("reason", 255);
		this.meta				= Dbmeta_tbl_itm.New(tbl_name, flds);
	}
	public Dbmeta_tbl_itm Meta() {return meta;} private final    Dbmeta_tbl_itm meta;
}
