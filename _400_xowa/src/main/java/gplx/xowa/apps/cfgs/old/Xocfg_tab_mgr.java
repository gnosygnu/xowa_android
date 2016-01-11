package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_tab_mgr implements GfoInvkAble {
	public Xocfg_tab_new_mgr New_mgr() {return new_mgr;} private Xocfg_tab_new_mgr new_mgr = new Xocfg_tab_new_mgr();
	public Xocfg_tab_btn_mgr Btn_mgr() {return btn_mgr;} private Xocfg_tab_btn_mgr btn_mgr = new Xocfg_tab_btn_mgr();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_new))				return new_mgr;
		else if	(ctx.Match(k, Invk_btns))				return btn_mgr;
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_new = "new", Invk_btns = "btns";
}
