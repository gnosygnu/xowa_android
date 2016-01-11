package gplx.dbs; import gplx.*;
import gplx.dbs.metas.*;
public class Db_conn_utl {
	public static Db_conn Conn__new(String url_rel) {
		Db_conn_bldr.Instance.Reg_default_mem();
		return Db_conn_bldr.Instance.Get_or_new(Io_url_.mem_fil_("mem/" + url_rel)).Conn();
	}
	public static void Tbl__delete(Db_conn conn, String tbl) {
		conn.Ddl_delete_tbl(tbl);
	}
	public static void Tbl__new(Db_conn conn, String tbl, Dbmeta_fld_itm[] flds, Object[]... rows) {
		conn.Ddl_create_tbl(Dbmeta_tbl_itm.New(tbl, flds));
		int rows_len = rows.length;
		Db_stmt stmt = conn.Stmt_insert(tbl, Dbmeta_fld_itm.To_str_ary(flds));
		for (int i = 0; i < rows_len; ++i) {
			Object[] row = rows[i];
			int dat_len = row.length;
			stmt.Clear();
			for (int j = 0; j < dat_len; ++j) {
				Dbmeta_fld_itm fld = flds[j];
				String fld_name = fld.Name();
				Object val = row[j];
				switch (fld.Type().Tid_ansi()) {
					case Dbmeta_fld_tid.Tid__bool:		stmt.Val_bool_as_byte	(fld_name, Bool_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__byte:		stmt.Val_byte			(fld_name, Byte_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__int:		stmt.Val_int			(fld_name, Int_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__long:		stmt.Val_long			(fld_name, Long_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__float:		stmt.Val_float			(fld_name, Float_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__double:	stmt.Val_double			(fld_name, Double_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__str:		stmt.Val_str			(fld_name, String_.cast(val)); break;
					case Dbmeta_fld_tid.Tid__bry:		stmt.Val_bry			(fld_name, Bry_.cast(val)); break;
				}
			}
			stmt.Exec_insert();
		}
	}
}
