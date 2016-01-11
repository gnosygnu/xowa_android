package gplx.xowa.files.fsdb.fs_roots; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*; import gplx.xowa.files.fsdb.*;
import gplx.dbs.*;
public class Orig_fil_tbl implements Rls_able {
	private String tbl_name = "orig_fil"; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private String fld_uid, fld_name, fld_ext_id, fld_w, fld_h, fld_dir_url;		
	private Db_conn conn; private Db_stmt stmt_insert, stmt_select;
	public void Conn_(Db_conn new_conn, boolean created, boolean schema_is_1) {
		this.conn = new_conn; flds.Clear();
		String fld_prefix = "";
		if (schema_is_1) {
			fld_prefix		= "fil_";
		}
		fld_uid				= flds.Add_int(fld_prefix + "uid");
		fld_name			= flds.Add_str(fld_prefix + "name", 1024);
		fld_ext_id			= flds.Add_int(fld_prefix + "ext_id");
		fld_w				= flds.Add_int(fld_prefix + "w");
		fld_h				= flds.Add_int(fld_prefix + "h");
		fld_dir_url			= flds.Add_str(fld_prefix + "dir_url", 1024);	// NOTE: don't put dir in separate table; note that entire root_dir_wkr is not built to scale due to need for recursively loading all files
		if (created) {
			Dbmeta_tbl_itm meta = Dbmeta_tbl_itm.New(tbl_name, flds
			, Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_name)
			);
			conn.Ddl_create_tbl(meta);
		}
		stmt_insert = stmt_select = null;
		conn.Rls_reg(this);
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
		stmt_select = Db_stmt_.Rls(stmt_select);
	}
	public Orig_fil_itm Select_itm(byte[] ttl) {
		if (stmt_select == null) stmt_select = conn.Stmt_select(tbl_name, flds, fld_name);
		Orig_fil_itm rv = Orig_fil_itm.Null;
		Db_rdr rdr = stmt_select.Clear().Crt_bry_as_str(fld_name, ttl).Exec_select__rls_manual();
		try {
			if (rdr.Move_next())
				rv = Load_itm(rdr);
			return rv;
		}
		finally {rdr.Rls();}
	}
	private Orig_fil_itm Load_itm(Db_rdr rdr) {
		return new Orig_fil_itm
		( rdr.Read_int(fld_uid)
		, rdr.Read_bry_by_str(fld_name)
		, rdr.Read_int(fld_ext_id)
		, rdr.Read_int(fld_w)
		, rdr.Read_int(fld_h)
		, rdr.Read_bry_by_str(fld_dir_url)
		);
	}
	public void Insert(Orig_fil_itm fil_itm) {
		if (stmt_insert == null) stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear()
		.Val_int(fld_uid, fil_itm.Fil_uid())
		.Val_bry_as_str(fld_name, fil_itm.Fil_name())
		.Val_int(fld_ext_id, fil_itm.Fil_ext_id())
		.Val_int(fld_w, fil_itm.Fil_w())
		.Val_int(fld_h, fil_itm.Fil_h())
		.Val_bry_as_str(fld_dir_url, fil_itm.Fil_dir_url())
		.Exec_insert();
	}	
}
