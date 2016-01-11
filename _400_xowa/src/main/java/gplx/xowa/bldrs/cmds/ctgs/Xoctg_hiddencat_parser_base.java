package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.sqls.*;
public abstract class Xoctg_hiddencat_parser_base extends Xob_sql_dump_base implements Sql_file_parser_cmd {
	public Xoctg_hiddencat_parser_base Ctor(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); this.make_fil_len = Io_mgr.Len_mb; return this;}
	@Override public String Sql_file_name() {return "page_props";}
	private static final byte Fld_id = 0, Fld_key = 1, Fld_val = 2;
	private int cur_id = -1;
	private boolean cur_is_hiddencat = false;
	private int rows = 0;
	@Override public void Cmd_bgn_hook(Xob_bldr bldr, Sql_file_parser parser) {
		parser.Fld_cmd_(this).Flds_req_idx_(4, 0, 1, 2);	// NOTE: 4 b/c MW added fld_3:pp_sortkey; DATE:2014-04-28
	}
	public void Exec(byte[] src, byte[] fld_key, int fld_idx, int fld_bgn, int fld_end, Bry_bfr file_bfr, Sql_file_parser_data data) {
		switch (fld_idx) {
			case Fld_id:			cur_id = Bry_.To_int_or(src, fld_bgn, fld_end, -1); break;
			case Fld_key:			cur_is_hiddencat = Bry_.Eq(src, fld_bgn, fld_end, Key_hiddencat); break;
			case Fld_val:
				if (!cur_is_hiddencat) {data.Cancel_row_y_(); return;}
				Exec_hook(file_bfr, cur_id, cur_is_hiddencat);
				if (++rows % 100000 == 0) usr_dlg.Prog_many("", "", "reading row ~{0}", Int_.To_str_fmt(rows, "#,##0"));
				break;
		}
	}
	public abstract void Exec_hook(Bry_bfr file_bfr, int cur_id, boolean cur_is_hiddencat);
	public static final byte[] Key_hiddencat = Bry_.new_a7("hiddencat");
}
