package gplx.xowa.bldrs.cmds.texts.tdbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.xowa.addons.searchs.dbs.bldrs.*;
public class Xob_search_tdb extends Srch_bldr_wkr_base {
	public Xob_search_tdb(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	@Override public String Wkr_key() {return Xob_cmd_keys.Key_tdb_make_search_title;}
	@Override public gplx.core.ios.Io_make_cmd Make_cmd_site() {
		return new Xob_make_cmd_site(bldr.Usr_dlg(), this.make_dir, this.make_fil_len);
	}
}
