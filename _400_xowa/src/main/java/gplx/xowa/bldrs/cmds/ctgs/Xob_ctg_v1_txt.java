package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*;
import gplx.xowa.bldrs.cmds.texts.tdbs.*;
public class Xob_ctg_v1_txt extends Xob_ctg_v1_base {
	@Override public String Wkr_key() {return Xob_cmd_keys.Key_tdb_make_category;}
	@Override public Io_sort_cmd Make_sort_cmd() {return new Xob_make_cmd_site(bldr.Usr_dlg(), make_dir, make_fil_len);}
}
