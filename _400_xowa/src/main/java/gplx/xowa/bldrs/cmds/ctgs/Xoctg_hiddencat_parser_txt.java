package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*; import gplx.xowa.bldrs.sqls.*;
public class Xoctg_hiddencat_parser_txt extends Xoctg_hiddencat_parser_base {
	public Xoctg_hiddencat_parser_txt(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_tdb_cat_hidden_sql;}
	@Override public void Cmd_bgn_hook(Xob_bldr bldr, Sql_file_parser parser) {
		super.Cmd_bgn_hook(bldr, parser);
	}
	@Override public void Exec_hook(Bry_bfr file_bfr, int cur_id, boolean cur_is_hiddencat) {
		fld_wtr.Bfr_(file_bfr);
		fld_wtr.Write_int_base85_len5_fld(cur_id);
	}
	@Override public void Cmd_end() {
		Xobdc_merger.Basic(bldr.Usr_dlg(), dump_url_gen, temp_dir.GenSubDir("sort"), sort_mem_len, Xoctg_link_sql_sorter.Instance, Io_line_rdr_key_gen_.noop, new Io_sort_fil_basic(bldr.Usr_dlg(), this.Make_url_gen(), make_fil_len));
	}
}
