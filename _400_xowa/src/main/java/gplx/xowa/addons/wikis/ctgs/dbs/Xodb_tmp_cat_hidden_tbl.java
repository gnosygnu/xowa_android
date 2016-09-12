package gplx.xowa.addons.wikis.ctgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.dbs.*;
public class Xodb_tmp_cat_hidden_tbl implements Db_tbl {
	private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	private final    String fld_cat_id;
	private Db_stmt stmt_insert;
	public Xodb_tmp_cat_hidden_tbl(Db_conn conn) {
		this.conn = conn;
		this.tbl_name = "tmp_cat_hidden";
		this.fld_cat_id			= flds.Add_int_pkey	("cat_id");
		conn.Rls_reg(this);
		conn.Meta_tbl_remake(this);
	}
	public Db_conn Conn() {return conn;} private final    Db_conn conn; 
	public String Tbl_name() {return tbl_name;} private final    String tbl_name; 
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Insert_bgn() {conn.Txn_bgn("tch__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end() {conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert_cmd_by_batch(int page_id) {
		stmt_insert.Clear()
			.Val_int(fld_cat_id					, page_id)
			.Exec_insert();
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
}
