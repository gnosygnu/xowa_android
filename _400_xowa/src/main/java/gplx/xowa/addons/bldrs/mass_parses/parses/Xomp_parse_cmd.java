package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.mass_parses.parses.mgrs.*;
public class Xomp_parse_cmd extends Xob_cmd__base {
	private final    Xomp_parse_mgr mgr = new Xomp_parse_mgr();
	public Xomp_parse_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		mgr.Run(wiki);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__cfg))		return mgr.Cfg();
		else	return super.Invk(ctx, ikey, k, m);
	}	private static final String Invk__cfg = "cfg";
	
	@Override public String Cmd_key() {return BLDR_CMD_KEY;}  private static final String BLDR_CMD_KEY = "wiki.mass_parse.exec";
	public static final    Xob_cmd Prototype = new Xomp_parse_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xomp_parse_cmd(bldr, wiki);}
}
