package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*;
public class Srch_temp_tbl {
	public final String tbl_name = "search_temp"; 
	private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_word_id, fld_page_id, fld_word_text;
	public final Db_conn conn; private Db_stmt stmt_insert;		
	public Srch_temp_tbl(Db_conn conn) {
		this.conn = conn;
		flds.Add_int_pkey_autonum("word_uid");
		fld_word_id			= flds.Add_int("word_id");
		fld_page_id			= flds.Add_int("page_id");
		fld_word_text		= flds.Add_str("word_text", 255);
	}
	public void Insert_bgn() {
		conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));
		stmt_insert = conn.Stmt_insert(tbl_name, flds);
		conn.Txn_bgn("schema__search_temp__insert");
	}
	public void Insert_cmd_by_batch(int word_id, int page_id, byte[] word) {
		stmt_insert.Clear().Val_int(fld_word_id, word_id).Val_int(fld_page_id, page_id).Val_bry_as_str(fld_word_text, word).Exec_insert();
	}
	public void Insert_end() {
		conn.Txn_end();
		stmt_insert = Db_stmt_.Rls(stmt_insert);
		conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_word_text, fld_page_id));
	}
}
