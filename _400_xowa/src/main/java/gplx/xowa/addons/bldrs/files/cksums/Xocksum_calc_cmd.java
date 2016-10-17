package gplx.xowa.addons.bldrs.files.cksums; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.files.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xocksum_calc_cmd extends Xob_cmd__base {
	public Xocksum_calc_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		new Xocksum_calc_mgr().Exec(wiki);
	}

	@Override public String Cmd_key() {return BLDR_CMD_KEY;}  private static final String BLDR_CMD_KEY = "fsdb.cksums.calc";
	public static final    Xob_cmd Prototype = new Xocksum_calc_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xocksum_calc_cmd(bldr, wiki);}
}
