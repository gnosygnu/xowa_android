package gplx.xowa.addons.wikis.pages.randoms.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.randoms.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Rndm_bldr_cmd extends Xob_cmd__base {
	private int rndm_interval = 1000;
	public Rndm_bldr_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		new Rndm_ns_rebuilder().Exec(wiki, rndm_interval);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_rndm_interval_))		rndm_interval = m.ReadInt("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_rndm_interval_ = "rndm_interval_";

	public static final String BLDR_CMD_KEY = "wiki.random";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Rndm_bldr_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Rndm_bldr_cmd(bldr, wiki);}
}
