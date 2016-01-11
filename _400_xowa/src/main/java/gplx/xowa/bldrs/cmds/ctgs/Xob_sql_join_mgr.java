package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*;
interface Xob_sql_join_wkr {
	Io_line_rdr New_main_rdr();
	Io_line_rdr New_join_rdr();
	void Process_match(Io_line_rdr main, Io_line_rdr join, byte[] key_bry);
}
class Xob_sql_join_mgr {
	public static void Exec_join(Xob_sql_join_wkr wkr) {
		Io_line_rdr main_rdr = wkr.New_main_rdr();
		Io_line_rdr join_rdr = wkr.New_join_rdr();
		while (main_rdr.Read_next()) {
			byte[] key_bry = Bry_.Mid(main_rdr.Bfr(), main_rdr.Key_pos_bgn(), main_rdr.Key_pos_end());
			if (join_rdr.Match(key_bry)) {
				wkr.Process_match(main_rdr, join_rdr, key_bry);
			}
		}
	}
}
