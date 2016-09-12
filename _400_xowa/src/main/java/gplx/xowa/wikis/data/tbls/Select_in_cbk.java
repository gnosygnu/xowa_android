package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*;
public interface Select_in_cbk {
	void Write_sql(Bry_bfr bfr, int idx);
	void Read_data(Db_rdr rdr);
	int Hash_max();
}
class Select_in_wkr {
	private final    byte[] sql_bgn;
	public Select_in_wkr(byte[] sql_bgn) {this.sql_bgn = sql_bgn;}
	public int Make_sql_or_null(Bry_bfr bfr, Select_in_cbk cbk, int bgn) {			
		// read 50 at a time
		int max = cbk.Hash_max();
		int end = bgn + 50; if (end > max) end = max;
		if (bgn == end) return -1; // at eos; return;

		// concat itms
		bfr.Add(sql_bgn);
		for (int i = bgn; i < end; ++i) {
			if (i != bgn) bfr.Add_str_a7(", ");
			cbk.Write_sql(bfr, i);
		}
		bfr.Add_byte(Byte_ascii.Paren_end);
		return end;
	}
	public static Select_in_wkr New(Bry_bfr bfr, String tbl_name, String[] select_flds, String where_fld) {
		bfr.Add_str_a7("SELECT ");
		int select_flds_len = select_flds.length;
		for (int i = 0; i < select_flds_len; ++i) {
			String select_fld = select_flds[i];
			if (i != 0) bfr.Add_str_a7(", ");
			bfr.Add_str_u8(select_fld);
		}
		bfr.Add_str_a7(" FROM ").Add_str_u8(tbl_name);
		bfr.Add_str_a7(" WHERE ").Add_str_u8(where_fld);
		bfr.Add_str_a7(" IN (");
		return new Select_in_wkr(bfr.To_bry_and_clear());
	}
}
