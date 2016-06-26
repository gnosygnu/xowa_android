package gplx.xowa.addons.bldrs.exports.packs.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Pack_file_bldr_cmd extends Xob_cmd__base {
	private Io_url deploy_dir;
	public Pack_file_bldr_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		new Pack_file_mgr().Exec(wiki, deploy_dir);
	}

	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__deploy_dir_))		deploy_dir = m.ReadIoUrl("v");
		else											return super.Invk (ctx, ikey, k, m);
		return this;
	}
	private static final String Invk__deploy_dir_ = "deploy_dir_";

	public static final String BLDR_CMD_KEY = "bldr.export.pack.file";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Pack_file_bldr_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Pack_file_bldr_cmd(bldr, wiki);}
}
