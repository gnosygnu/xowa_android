package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.core.ios.*; import gplx.dbs.*; import gplx.dbs.utls.*;
public class Xowd_text_tbl implements Rls_able {
	private final String tbl_name = "text"; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_page_id, fld_text_data;
	private final Db_conn conn; private Db_stmt stmt_select, stmt_insert;
	private final Io_stream_zip_mgr zip_mgr = Xoa_app_.Utl__zip_mgr(); private final byte zip_tid;
	public String Fld_text_data() {return fld_text_data;}
	public Xowd_text_tbl(Db_conn conn, boolean schema_is_1, byte zip_tid) {
		this.conn = conn; this.zip_tid = zip_tid;
		String fld_text_data_name = "";
		fld_text_data_name = schema_is_1 ? "old_text" : "text_data";
		fld_page_id			= flds.Add_int_pkey("page_id");
		fld_text_data		= flds.Add_bry(fld_text_data_name);
		conn.Rls_reg(this);
	}
	public void Create_tbl() {conn.Ddl_create_tbl(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Insert_bgn() {conn.Txn_bgn("schema__text__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end() {conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert_cmd_by_batch(int page_id, byte[] text_data) {
		stmt_insert.Clear().Val_int(fld_page_id, page_id).Val_bry(fld_text_data, text_data).Exec_insert();
	}
	public void Update(int page_id, byte[] text) {
		Db_stmt stmt = conn.Stmt_update_exclude(tbl_name, flds, fld_page_id);
		text = zip_mgr.Zip(zip_tid, text);
		stmt.Clear().Val_bry(fld_text_data, text).Crt_int(fld_page_id, page_id).Exec_update();
	}
	public byte[] Select(int page_id) {
		if (stmt_select == null) stmt_select = conn.Stmt_select(tbl_name, flds, fld_page_id);
		Db_rdr rdr = stmt_select.Clear().Val_int(fld_page_id, page_id).Exec_select__rls_manual();
		try {
			byte[] rv = (byte[])rdr.Read_bry(fld_text_data);
			if (rv == null) rv = Bry_.Empty;	// NOTE: defect wherein blank page inserts null not ""; for now always convert nul to empty String; DATE:2015-11-08
			rv = zip_mgr.Unzip(zip_tid, rv);
			return rv;
		}	finally {rdr.Rls();}
	}
	public byte[] Zip(byte[] data) {return zip_mgr.Zip(zip_tid, data);}
	public void Rls() {
		stmt_select = Db_stmt_.Rls(stmt_select);
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
}
