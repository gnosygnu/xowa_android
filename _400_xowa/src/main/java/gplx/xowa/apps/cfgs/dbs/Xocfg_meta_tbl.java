package gplx.xowa.apps.cfgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
import gplx.dbs.*;
public class Xocfg_meta_tbl implements Rls_able {
	private final    String tbl_name; public final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	private final    String fld_key, fld_type, fld_dflt, fld_version;
	private final    Db_conn conn;
	public Xocfg_meta_tbl(Db_conn conn) {
		this.conn = conn;
		tbl_name			= Tbl_name;
		fld_key				= flds.Add_str_pkey	("cfg_key"		, 1024);		// EX: "xowa.net.web_enabled"
		fld_type			= flds.Add_str		("cfg_type"		, 255);			// EX: "yn"
		fld_dflt			= flds.Add_str		("cfg_dflt"		, 1024);		// EX: "n"
		fld_version			= flds.Add_str		("cfg_version"	, 16);			// EX: "v1.1.1.1"
	}
	public void Create_tbl()		{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Insert(String key, String type, String dflt, String version) {
		Db_stmt stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear().Val_str(fld_key, key).Val_str(fld_type, type).Val_str(fld_dflt, dflt).Val_str(fld_version, version)
			.Exec_insert();
	}
	public void Rls() {}
	public static final String Tbl_name = "cfg_meta";
}
