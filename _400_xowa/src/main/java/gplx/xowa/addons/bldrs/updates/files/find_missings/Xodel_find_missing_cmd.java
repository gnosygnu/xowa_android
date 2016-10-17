package gplx.xowa.addons.bldrs.updates.files.find_missings; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*; import gplx.xowa.addons.bldrs.updates.files.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xodel_find_missing_cmd extends Xob_cmd__base implements Xob_cmd {
	public Xodel_find_missing_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		new Xodel_find_missing_mgr().Exec(wiki);
	}

	public static final String BLDR_CMD_KEY = "file.deletion_db.find_missing";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xodel_find_missing_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xodel_find_missing_cmd(bldr, wiki);}
}
