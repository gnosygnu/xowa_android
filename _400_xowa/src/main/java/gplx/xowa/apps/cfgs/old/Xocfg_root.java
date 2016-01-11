package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_root implements GfoInvkAble {
	public Xocfg_root(Xoae_app app, byte tid) {
		this.tid = tid;
		this.gui_mgr = new Xocfg_gui_mgr(app);
	}
	public byte Tid() {return tid;} private byte tid;
	public Xocfg_gui_mgr Gui_mgr() {return gui_mgr;} private Xocfg_gui_mgr gui_mgr;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_gui))				return gui_mgr;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_gui = "gui";
}
