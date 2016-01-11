package gplx.xowa.bldrs.cmds.texts.tdbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.xowa.bldrs.cmds.texts.*;
public class Xob_term_txt extends Xob_term_base {
	public Xob_term_txt(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki); this.wiki = wiki;} private Xowe_wiki wiki;
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_tdb_core_term;}
	@Override public void Cmd_end_hook() {
		Io_mgr.Instance.SaveFilBry(wiki.Tdb_fsys_mgr().Cfg_wiki_core_fil(), wiki.Cfg_wiki_core().Build_gfs());
	}
}
