package gplx.xowa.specials.xowa.diags; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.*;
import gplx.core.stores.*;
import gplx.dbs.*;
import gplx.dbs.engines.mems.*;
class Db_rdr_utl {
	public static void Load_and_write(Db_conn conn, String sql, Bry_bfr bfr) {
		Write_to_bfr(bfr, Load(conn, sql));
	}
	public static Mem_qry_set Load_as_qry_set(Db_conn conn, Dbmeta_fld_list fld_list, String sql) {
		Mem_qry_set qry_set = new Mem_qry_set();
		DataRdr rdr = conn.Exec_sql_as_rdr(sql);
		try {
			int fld_count = rdr.FieldCount();
			while (rdr.MoveNextPeer()) {
				Mem_row row = new Mem_row();
				for (int i = 0; i < fld_count; ++i)						
					row.Add(fld_list.Get_at(i).Name(), rdr.ReadAt(i));
				qry_set.Add(row);
			}
		}
		finally {
			rdr.Rls();
		}
		return qry_set;
	}
	public static Object[][] Load(Db_conn conn, String sql) {
		List_adp list = List_adp_.new_();
		DataRdr rdr = conn.Exec_sql_as_rdr(sql);
		try {
			int fld_count = rdr.FieldCount();
			while (rdr.MoveNextPeer()) {
				Object[] row = new Object[fld_count];
				for (int i = 0; i < fld_count; ++i) {
					row[i] = rdr.ReadAt(i);
				}
				list.Add(row);
			}
		}
		finally {
			rdr.Rls();
		}
		return (Object[][])list.To_ary_and_clear(Object[].class);
	}
	public static void Write_to_bfr(Bry_bfr bfr, Object[][] rows, int... skip) {
		int rows_len = rows.length;
		for (int i = 0; i < rows_len; ++i) {
			Object[] row = (Object[])rows[i];
			int row_len = row.length;
			for (int j = 0; j < row_len; ++j) {
				if (skip != null && Int_.In(j, skip)) continue;
				Object val = row[j];
				bfr.Add_obj(val).Add_byte_pipe();
			}
			bfr.Add_byte_nl();
		}
	}
}
