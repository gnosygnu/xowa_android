package gplx.xowa.addons.apps.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*;
import gplx.dbs.*;
public class Srch_link_reg_tbl implements Rls_able {
	private final    String tbl_name; public final    Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final    String fld_id, fld_db_id, fld_db_type, fld_ns_ids, fld_sub_id, fld_score_min, fld_score_max;
	private final    Db_conn conn;
	public Srch_link_reg_tbl(Db_conn conn) {
		this.conn = conn;
		tbl_name			= Tbl_name;
		fld_id				= flds.Add_int_pkey	("reg_id");			// corresponds to link_tbl_ary_idx
		fld_db_id			= flds.Add_int		("reg_db_id");		// corresponds to xowa_db
		fld_db_type			= flds.Add_int		("reg_db_type");	// "title"; "text"
		fld_ns_ids			= flds.Add_int		("reg_ns_ids");		// "0"; "*"
		fld_sub_id			= flds.Add_int		("reg_sub_id");		// "0"; "*"
		fld_score_min		= flds.Add_int		("reg_score_min");	// -1
		fld_score_max		= flds.Add_int		("reg_score_max");	// -1
	}
	public void Create_tbl()		{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Rls() {}
	public void Insert(int id, int db_id, String db_type, String ns_ids, int sub_id, int score_min, int score_max) {
		Db_stmt stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear().Val_int(fld_id, id).Val_int(fld_db_id, db_id).Val_str(fld_db_type, db_type).Val_str(fld_ns_ids, ns_ids).Val_int(fld_sub_id, sub_id).Val_int(fld_score_min, score_min).Val_int(fld_score_max, score_max).Exec_insert();
	}
	public static final String Tbl_name = "search_link_reg";
	public static final String Db_type__title	= "title"	, Db_type__text = "text";
	public static final String Ns_type__main	= "0"		, Ns_type__rest = "*";
}
