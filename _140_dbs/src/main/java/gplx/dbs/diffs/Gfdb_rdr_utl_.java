package gplx.dbs.diffs; import gplx.*; import gplx.dbs.*;
import gplx.dbs.metas.*;
public class Gfdb_rdr_utl_ {
	public static int Compare(Dbmeta_fld_itm[] flds, int len, Db_rdr lhs_rdr, Db_rdr rhs_rdr) {
		int comp = CompareAble_.Same;
		for (int i = 0; i < len; ++i) {
			Dbmeta_fld_itm fld = flds[i];
			String fld_name = fld.Name();
			int tid = fld.Type().Tid_ansi();
			switch (tid) {
				case Dbmeta_fld_tid.Tid__bool:		comp = Bool_.Compare	(lhs_rdr.Read_bool_by_byte(fld_name), rhs_rdr.Read_bool_by_byte(fld_name)); break;
				case Dbmeta_fld_tid.Tid__int:		comp = Int_.Compare		(lhs_rdr.Read_int(fld_name)			, rhs_rdr.Read_int(fld_name)); break;
				case Dbmeta_fld_tid.Tid__long:		comp = Long_.Compare	(lhs_rdr.Read_long(fld_name)		, rhs_rdr.Read_long(fld_name)); break;
				case Dbmeta_fld_tid.Tid__float:		comp = Float_.Compare	(lhs_rdr.Read_float(fld_name)		, rhs_rdr.Read_float(fld_name)); break;
				case Dbmeta_fld_tid.Tid__double:	comp = Double_.Compare	(lhs_rdr.Read_double(fld_name)		, rhs_rdr.Read_double(fld_name)); break;
				case Dbmeta_fld_tid.Tid__str:		comp = String_.Compare	(lhs_rdr.Read_str(fld_name)			, rhs_rdr.Read_str(fld_name)); break;
				case Dbmeta_fld_tid.Tid__bry:		comp = Bry_.Compare		(lhs_rdr.Read_bry(fld_name)			, rhs_rdr.Read_bry(fld_name)); break;
				default:							throw Err_.new_unhandled(tid);
			}
			if (comp != CompareAble_.Same) return comp;
		}
		return CompareAble_.Same;
	}
	public static void Stmt_args(Db_stmt stmt, Dbmeta_fld_itm[] flds, int len, Db_rdr rdr) {
		for (int i = 0; i < len; ++i) {
			Dbmeta_fld_itm fld = flds[i];
			String fld_name = fld.Name();
			int tid = fld.Type().Tid_ansi();
			switch (tid) {
				case Dbmeta_fld_tid.Tid__bool:		stmt.Val_bool_as_byte	(fld_name, rdr.Read_bool_by_byte(fld_name)); break;
				case Dbmeta_fld_tid.Tid__byte:		stmt.Val_byte			(fld_name, rdr.Read_byte(fld_name)); break;
				case Dbmeta_fld_tid.Tid__int:		stmt.Val_int			(fld_name, rdr.Read_int(fld_name)); break;
				case Dbmeta_fld_tid.Tid__long:		stmt.Val_long			(fld_name, rdr.Read_long(fld_name)); break;
				case Dbmeta_fld_tid.Tid__float:		stmt.Val_float			(fld_name, rdr.Read_float(fld_name)); break;
				case Dbmeta_fld_tid.Tid__double:	stmt.Val_double			(fld_name, rdr.Read_double(fld_name)); break;
				case Dbmeta_fld_tid.Tid__str:		stmt.Val_str			(fld_name, rdr.Read_str(fld_name)); break;
				case Dbmeta_fld_tid.Tid__bry:		stmt.Val_bry			(fld_name, rdr.Read_bry(fld_name)); break;
				default:							throw Err_.new_unhandled(tid);
			}
		}
	}
}
