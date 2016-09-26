package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xodel_exec_cmd extends Xob_cmd__base {
	private Io_url deletion_db_url;
	public Xodel_exec_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		new Xodel_exec_mgr().Exec_delete(gplx.core.progs.Gfo_prog_ui_.Noop, bldr, deletion_db_url);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if	(ctx.Match(k, Invk__file_))			this.deletion_db_url = Io_url_.new_any_(m.ReadStr("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk__file_ = "file_";

	public static final String BLDR_CMD_KEY = "fsdb.deletion_db.exec";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xodel_exec_cmd(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xodel_exec_cmd(bldr, wiki);}
}
