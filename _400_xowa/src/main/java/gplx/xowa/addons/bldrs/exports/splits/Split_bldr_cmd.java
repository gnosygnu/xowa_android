package gplx.xowa.addons.bldrs.exports.splits; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.exports.splits.mgrs.*;
public class Split_bldr_cmd extends Xob_cmd__base {
	private final    Split_cfg cfg = new Split_cfg();
	public Split_bldr_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		wiki.Init_assert();
		new Split_mgr().Exec(wiki, cfg);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__cfg))				return cfg;
		return super.Invk (ctx, ikey, k, m);
	}
	private static final String Invk__cfg = "cfg";

	public static final String BLDR_CMD_KEY = "bldr.export.split";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Split_bldr_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Split_bldr_cmd(bldr, wiki);}
}
