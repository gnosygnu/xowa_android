package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.core.ios.*; import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.dbs.*; import gplx.dbs.cfgs.*;
public class Xowd_pagelinks_tbl implements Rls_able {
	private final String tbl_name = "pagelink"; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_src_id, fld_trg_id;
	private final Db_conn conn;
	public Xowd_pagelinks_tbl(Db_conn conn) {
		this.conn = conn;
		fld_src_id			= flds.Add_int("src_id");
		fld_trg_id			= flds.Add_int("trg_id");
		flds.Add_int("trg_count");
		conn.Rls_reg(this);
	}
	public Db_conn Conn() {return conn;}
	public String Tbl_name() {return tbl_name;}
	public void Create_tbl() {conn.Ddl_create_tbl(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx__src_trg() {conn.Ddl_create_idx(Gfo_usr_dlg_.Instance, Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "src_trg", fld_src_id, fld_trg_id));}
	public void Create_idx__trg_src() {conn.Ddl_create_idx(Gfo_usr_dlg_.Instance, Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "trg_src", fld_trg_id, fld_src_id));}
	public void Rls() {}
}
