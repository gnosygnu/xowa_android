package gplx.xowa.bldrs.installs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.gfui.*; import gplx.xowa.bldrs.setups.addons.*;
import gplx.xowa.bldrs.setups.maints.*;
public class Xoi_setup_mgr implements GfoInvkAble {
	public Xoi_setup_mgr(Xoae_app app) {
		this.app = app;
		cmd_mgr = new Xoi_cmd_mgr(this);
		maint_mgr = new Xoa_maint_mgr(app);
	}
	public void Init_by_app(Xoae_app app) {
		addon_mgr.Init_by_app(app);
	}
	public Xoae_app App() {return app;} private Xoae_app app;
	public Xoi_cmd_mgr Cmd_mgr() {return cmd_mgr;} private Xoi_cmd_mgr cmd_mgr;
	public Xoi_dump_mgr Dump_mgr() {return dump_mgr;} private Xoi_dump_mgr dump_mgr = new Xoi_dump_mgr();
	public Xoi_addon_mgr Addon_mgr() {return addon_mgr;} private Xoi_addon_mgr addon_mgr = new Xoi_addon_mgr();
	public Xoa_maint_mgr Maint_mgr() {return maint_mgr;} private Xoa_maint_mgr maint_mgr;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_cmds))								return cmd_mgr;
		else if	(ctx.Match(k, Invk_dumps))								return dump_mgr;
		else if	(ctx.Match(k, Invk_addons))								return addon_mgr;
		else if	(ctx.Match(k, Invk_maint))								return maint_mgr;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	static final String Invk_cmds = "cmds", Invk_dumps = "dumps", Invk_addons = "addons", Invk_maint = "maint";
	static final String GRP_KEY = "xowa.setup";
}
