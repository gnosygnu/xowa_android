package gplx.xowa.addons.builds.pagelinks.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.pagelinks.*;
import gplx.core.ios.*; import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.dbs.*; import gplx.dbs.cfgs.*;
public class Pglnk_page_link_temp_tbl implements Rls_able {
	private final    String tbl_name = "page_link_temp"; private final    Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final    String fld_src_id, fld_trg_ns, fld_trg_ttl;
	private final    Db_conn conn; private Db_stmt stmt_insert;
	public Pglnk_page_link_temp_tbl(Db_conn conn) {
		this.conn = conn;
		flds.Add_int_pkey_autonum("uid");
		fld_src_id			= flds.Add_int("src_id");
		fld_trg_ns			= flds.Add_int("trg_ns");
		fld_trg_ttl			= flds.Add_str("trg_ttl", 255);
		conn.Rls_reg(this);
	}
	public Db_conn Conn() {return conn;}
	public String Tbl_name()	{return tbl_name;}
	public void Create_tbl()	{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx()	{conn.Meta_idx_create(Gfo_usr_dlg_.Instance, Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "main", fld_src_id, fld_trg_ns, fld_trg_ttl));}
	public void Insert_bgn()	{conn.Txn_bgn("schema__page_link__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end()	{conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert(int src_id, int trg_ns, byte[] trg_ttl) {
		if (stmt_insert == null) stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear().Val_int(fld_src_id, src_id).Val_int(fld_trg_ns, trg_ns).Val_bry_as_str(fld_trg_ttl, trg_ttl).Exec_insert();
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
}
