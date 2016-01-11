package gplx.xowa.bldrs.cmds.texts.tdbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.xowa.xtns.wdatas.imports.*;
public class Xob_init_tdb extends Xob_init_base {
	public Xob_init_tdb(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_tdb_text_init;}
	@Override public void Cmd_ini_wdata(Xob_bldr bldr, Xowe_wiki wiki) {
		bldr.Cmd_mgr().Add_cmd(wiki, Xob_cmd_keys.Key_tdb_text_wdata_qid);
		bldr.Cmd_mgr().Add_cmd(wiki, Xob_cmd_keys.Key_tdb_text_wdata_pid);
	}
	@Override public void Cmd_run_end(Xowe_wiki wiki) {}
}
