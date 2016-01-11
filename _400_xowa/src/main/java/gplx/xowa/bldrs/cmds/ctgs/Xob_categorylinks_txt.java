package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*;
import gplx.xowa.bldrs.sqls.*;
public class Xob_categorylinks_txt extends Xob_categorylinks_base {
	public Xob_categorylinks_txt(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); this.make_fil_len = Io_mgr.Len_mb;}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_tdb_text_cat_link;}
	@Override public Io_sort_cmd Make_sort_cmd(Sql_file_parser sql_parser) {return new Io_sort_fil_basic(bldr.Usr_dlg(), this.Make_url_gen(), make_fil_len);}
}
