package gplx.xowa.users.history; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xou_history_cfg implements GfoInvkAble {
	public Xou_history_cfg() {
		this.enabled = true;	// CFG: default to true for general user; privacy-conscious users must disable
	}
	public boolean Enabled() {return enabled;} private boolean enabled;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled_))				enabled = m.ReadBool("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_enabled_ = "enabled_";
}
