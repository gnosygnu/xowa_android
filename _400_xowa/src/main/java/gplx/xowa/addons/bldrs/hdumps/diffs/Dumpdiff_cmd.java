package gplx.xowa.addons.bldrs.hdumps.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.hdumps.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.htmls.core.htmls.*;
public class Dumpdiff_cmd extends Xob_cmd__base {
	private final    Dumpdiff_cfg cfg = new Dumpdiff_cfg();
	private final    Dumpdiff_mgr mgr = new Dumpdiff_mgr();
	public Dumpdiff_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		mgr.Exec(bldr.App(), wiki, cfg);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__cfg))					return cfg;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk__cfg = "cfg";

	public static final String BLDR_CMD_KEY = "hdump.diff";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Dumpdiff_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Dumpdiff_cmd(bldr, wiki);}
}
