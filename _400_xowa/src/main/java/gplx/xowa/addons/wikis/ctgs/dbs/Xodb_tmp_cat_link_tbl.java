package gplx.xowa.addons.wikis.ctgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.addons.wikis.ctgs.*; 
public class Xodb_tmp_cat_link_tbl implements Db_tbl {
	private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	private final    String fld_from, fld_to_ttl, fld_sortkey, fld_timestamp, fld_sortkey_prefix, fld_collation_id, fld_type_id;
	private Db_stmt stmt_insert;
	public Xodb_tmp_cat_link_tbl(Db_conn conn) {
		this.conn = conn;
		this.tbl_name = "tmp_cat_link";
		this.fld_from			= flds.Add_int	("cl_from");
		this.fld_to_ttl			= flds.Add_str	("cl_to_ttl", 255);
		this.fld_sortkey		= flds.Add_str	("cl_sortkey", 230);
		this.fld_timestamp		= flds.Add_long	("cl_timestamp");
		this.fld_sortkey_prefix	= flds.Add_str	("cl_sortkey_prefix", 230);
		this.fld_collation_id	= flds.Add_byte	("cl_collation_id");
		this.fld_type_id		= flds.Add_byte	("cl_type_id");
		conn.Rls_reg(this);
		conn.Meta_tbl_remake(this);
	}
	public Db_conn Conn() {return conn;} private final    Db_conn conn; 
	public String Tbl_name() {return tbl_name;} private final    String tbl_name; 
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Insert_bgn() {conn.Txn_bgn("tcl__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end() {conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert_cmd_by_batch(int page_id, byte[] ctg_ttl, byte[] sortkey, long timestamp, byte[] sortkey_prefix, byte collation_id, byte type_id) {
		stmt_insert.Clear()
			.Val_int(fld_from					, page_id)
			.Val_bry_as_str(fld_to_ttl			, ctg_ttl)
			.Val_bry_as_str(fld_sortkey			, sortkey)
			.Val_long(fld_timestamp				, timestamp)
			.Val_bry_as_str(fld_sortkey_prefix	, sortkey_prefix)
			.Val_byte(fld_collation_id			, collation_id)
			.Val_byte(fld_type_id				, type_id)
			.Exec_insert();
	}
	public void Create_idx__sortkey()	{conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, fld_sortkey, fld_sortkey));}
	public void Create_idx__to_ttl()	{conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, fld_to_ttl, fld_to_ttl));}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
}
