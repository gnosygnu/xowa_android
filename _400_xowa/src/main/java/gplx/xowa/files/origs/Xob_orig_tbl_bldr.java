package gplx.xowa.files.origs; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.dbs.*; import gplx.dbs.engines.sqlite.*; import gplx.fsdb.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xob_orig_tbl_bldr extends Xob_itm_basic_base implements Xob_cmd {
	private Db_conn conn; private boolean schema_1;
	public Xob_orig_tbl_bldr(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Xob_cmd_keys.Key_file_orig_reg;}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {
		Xof_fsdb_mode fsdb_mode = wiki.File__fsdb_mode();
		fsdb_mode.Tid_v2_bld_y_();
		wiki.Init_assert();
		Fsdb_db_mgr db_core_mgr = Fsdb_db_mgr_.new_detect(wiki, wiki.Fsys_mgr().Root_dir(), wiki.Fsys_mgr().File_dir());
		this.schema_1 = db_core_mgr.File__schema_is_1();
		conn = db_core_mgr.File__orig_tbl_ary()[gplx.fsdb.meta.Fsm_mnt_mgr.Mnt_idx_main].Conn();
		Io_url make_db_url = Xob_db_file.new__file_make(wiki.Fsys_mgr().Root_dir()).Url();
		Sqlite_engine_.Db_attach(conn, "make_db", make_db_url.Raw());
	}
	public void Cmd_run() {Exec();}
	public void Cmd_end() {}
	public void Cmd_term() {}
	private void Exec() {
		String tbl_name = "orig_reg", fld_status = "orig_status";
		if (schema_1) {
			tbl_name = "wiki_orig";
			fld_status = "status";
		}
		conn.Exec_sql_plog_txn("orig_wkr.deleting orig_reg"			, String_.Format(Sql_delete_wiki_orig, tbl_name)); // always delete orig_reg, else will not pick up changed sizes / moved repos; DATE:2014-07-21
		conn.Exec_sql_plog_txn("orig_wkr.inserting xfer direct"		, String_.Format(Sql_create_xfer_direct, tbl_name, fld_status));
		conn.Exec_sql_plog_txn("orig_wkr.inserting xfer redirect"	, String_.Format(Sql_create_xfer_redirect, tbl_name, fld_status));
		conn.Exec_sql_plog_txn("orig_wkr.inserting orig direct"		, String_.Format(Sql_create_orig_direct, tbl_name, fld_status));
		conn.Exec_sql_plog_txn("orig_wkr.inserting orig redirect"	, String_.Format(Sql_create_orig_redirect, tbl_name, fld_status));
	}
	private static final String 
		Sql_delete_wiki_orig = "DELETE FROM {0};"
	,	Sql_create_xfer_direct = String_.Concat_lines_nl
	(	"INSERT INTO {0} "
	,	"(orig_ttl, {1}, orig_repo, orig_ext, orig_w, orig_h, orig_redirect)"
	,	"SELECT DISTINCT"
	,	"        xfer.lnki_ttl"
	,	",       1 --pass"
	,	",       xfer.orig_repo"
	,	",       xfer.lnki_ext"
	,	",       xfer.orig_w"
	,	",       xfer.orig_h"
	,	",       ''"
	,	"FROM    make_db.xfer_regy xfer"
	,	"        LEFT JOIN {0} cur ON xfer.lnki_ttl = cur.orig_ttl"
	,	"WHERE   cur.orig_ttl IS NULL"
	)
	,	Sql_create_xfer_redirect = String_.Concat_lines_nl
	(	"INSERT INTO {0} "
	,	"(orig_ttl, {1}, orig_repo, orig_ext, orig_w, orig_h, orig_redirect)"
	,	"SELECT DISTINCT"
	,	"        xfer.orig_redirect_src"
	,	",       1 --pass"
	,	",       xfer.orig_repo"
	,	",       xfer.lnki_ext"
	,	",       xfer.orig_w"
	,	",       xfer.orig_h"
	,	",       xfer.lnki_ttl"
	,	"FROM    make_db.xfer_regy xfer"
	,	"        LEFT JOIN {0} cur ON xfer.orig_redirect_src = cur.orig_ttl"
	,	"WHERE   cur.orig_ttl IS NULL"
	,	"AND     Coalesce(xfer.orig_redirect_src, '') != ''"
	) 
	,	Sql_create_orig_direct = String_.Concat_lines_nl
	(	"INSERT INTO {0} "
	,	"(orig_ttl, {1}, orig_repo, orig_ext, orig_w, orig_h, orig_redirect)"
	,	"SELECT DISTINCT"
	,	"        orig.lnki_ttl"
	,	",       0 --unknown"
	,	",       orig.orig_repo"
	,	",       orig.lnki_ext"
	,	",       orig.orig_w"
	,	",       orig.orig_h"
	,	",       ''"
	,	"FROM    make_db.orig_regy orig"
	,	"        LEFT JOIN {0} cur ON orig.lnki_ttl = cur.orig_ttl"
	,	"WHERE   cur.orig_ttl IS NULL"							// not already in orig_reg
	,	"AND     orig.orig_repo IS NOT NULL"					// not found in oimg_image.sqlite3
	,	"AND     Coalesce(orig.orig_w           , -1) != -1"	// ignore entries that are either ext_id = 0 ("File:1") or don't have any width / height info (makes it useless); need to try to get again from wmf_api
	,	"AND     Coalesce(orig.orig_redirect_ttl, '') == ''"	// direct
	)
	,	Sql_create_orig_redirect = String_.Concat_lines_nl
	(	"INSERT INTO {0} "
	,	"(orig_ttl, {1}, orig_repo, orig_ext, orig_w, orig_h, orig_redirect)"
	,	"SELECT DISTINCT"
	,	"        orig.orig_redirect_ttl"
	,	",       0 --unknown"
	,	",       orig.orig_repo"
	,	",       orig.lnki_ext"
	,	",       orig.orig_w"
	,	",       orig.orig_h"
	,	",       ''"
	,	"FROM    make_db.orig_regy orig"
	,	"        LEFT JOIN {0} cur ON orig.orig_redirect_ttl = cur.orig_ttl"
	,	"WHERE   cur.orig_ttl IS NULL"							// not already in orig_reg
	,	"AND     orig.orig_repo IS NOT NULL"					// not found in oimg_image.sqlite3
	,	"AND     Coalesce(orig.orig_w,            -1) != -1"	// ignore entries that are either ext_id = 0 ("File:1") or don't have any width / height info (makes it useless); need to try to get again from wmf_api
	,	"AND     Coalesce(orig.orig_redirect_ttl, '') != ''"	// redirect
	)
	; 
}
