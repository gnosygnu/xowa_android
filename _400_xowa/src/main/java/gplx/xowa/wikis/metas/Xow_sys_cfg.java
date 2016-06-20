package gplx.xowa.wikis.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_sys_cfg implements Gfo_invk {
	public Xow_sys_cfg(Xowe_wiki wiki) {}
	public boolean Xowa_cmd_enabled() {return xowa_cmd_enabled;} public Xow_sys_cfg Xowa_cmd_enabled_(boolean v) {xowa_cmd_enabled = v; return this;} private boolean xowa_cmd_enabled;
	public boolean Xowa_proto_enabled() {return xowa_proto_enabled;} public Xow_sys_cfg Xowa_proto_enabled_(boolean v) {xowa_proto_enabled = v; return this;} private boolean xowa_proto_enabled;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_xowa_cmd_enabled_))			xowa_cmd_enabled	= m.ReadYn("v");
		else if	(ctx.Match(k, Invk_xowa_cmd_enabled_))			xowa_proto_enabled	= m.ReadYn("v");
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_xowa_cmd_enabled_ = "xowa_cmd_enabled_";
}
