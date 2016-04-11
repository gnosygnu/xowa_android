package gplx.xowa.addons.builds.files.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.files.*;
import gplx.dbs.*;
public class Page_file_map_tbl {
	public final    String tbl_name = "page_file_map";
	public final    Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	public final    String fld_page_id, fld_fil_id, fld_thm_id, fld_sort_id, fld_count_of;
	public final    Db_conn conn;
	public Page_file_map_tbl(Db_conn conn) {
		this.conn = conn;
		this.fld_page_id		= flds.Add_int("page_id");
		this.fld_fil_id			= flds.Add_int("fil_id");
		this.fld_thm_id			= flds.Add_int("thm_id");
		this.fld_sort_id		= flds.Add_int("sort_id");
		this.fld_count_of		= flds.Add_int("count_of");
		this.meta				= Dbmeta_tbl_itm.New(tbl_name, flds);
	}
	public Dbmeta_tbl_itm Meta() {return meta;} private final    Dbmeta_tbl_itm meta;
}
