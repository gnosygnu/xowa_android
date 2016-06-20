package gplx.xowa.bldrs.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.strings.*;
public interface Sql_file_parser_cmd {
	void Exec(byte[] src, byte[] fld_key, int fld_idx, int fld_bgn, int fld_end, Bry_bfr file_bfr, Sql_file_parser_data data);
}
class Sql_file_parser_cmd_max_len implements Sql_file_parser_cmd {
	public void Log_len_(int v) {log_len = v;} private int log_len = 141;
	public void Log_print(Io_url url) {
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < log.Count(); i++) {
			String itm = (String)log.Get_at(i);
			sb.Add(String_.Len(itm) + "|" + itm + "\n");
		}
		Io_mgr.Instance.SaveFilStr(url, sb.To_str());
	}
	public int Max_len() {return max_len;} private int max_len; 
	public void Exec(byte[] src, byte[] fld_key, int fld_idx, int fld_bgn, int fld_end, Bry_bfr file_bfr, Sql_file_parser_data data) {
		int fld_len = fld_end - fld_bgn;
		if (fld_len > max_len) max_len = fld_len;
		if (fld_len > log_len) {
			log.Add(String_.new_u8(src, fld_bgn, fld_end));
		}
		file_bfr.Add_mid(src, fld_bgn, fld_end).Add_byte(Byte_ascii.Pipe);
	}
	List_adp log = List_adp_.New();
}
