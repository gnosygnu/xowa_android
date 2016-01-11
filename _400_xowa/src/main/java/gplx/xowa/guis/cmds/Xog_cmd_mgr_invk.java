package gplx.xowa.guis.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
public class Xog_cmd_mgr_invk implements GfoInvkAble {
	private Xoae_app app; private Xog_cmd_mgr cmd_mgr;
	public void Ctor(Xoae_app app, Xog_cmd_mgr cmd_mgr) {this.app = app; this.cmd_mgr = cmd_mgr;}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		Xog_cmd_itm cmd_itm = cmd_mgr.Get_or_null(k);
		if (cmd_itm == null) return GfoInvkAble_.Rv_unhandled;
		return app.Gfs_mgr().Run_str(cmd_itm.Cmd());
	}
}
