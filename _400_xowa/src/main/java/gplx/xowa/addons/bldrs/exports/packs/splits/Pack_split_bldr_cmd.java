package gplx.xowa.addons.bldrs.exports.packs.splits; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Pack_split_bldr_cmd extends Xob_cmd__base {
	private long pack_size_max = 30 * Io_mgr.Len_mb;
	public Pack_split_bldr_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		new Pack_mgr().Exec(wiki, pack_size_max);
	}

	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__pack_size_max_))		pack_size_max = m.ReadLong("v");
		return super.Invk (ctx, ikey, k, m);
	}
	private static final String Invk__pack_size_max_ = "pack_size_max_";

	public static final String BLDR_CMD_KEY = "bldr.export.pack.split";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Pack_split_bldr_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Pack_split_bldr_cmd(bldr, wiki);}
}
