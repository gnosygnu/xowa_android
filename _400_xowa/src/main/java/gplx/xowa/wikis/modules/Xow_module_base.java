package gplx.xowa.wikis.modules; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_module_base implements GfoInvkAble {
	public byte Enabled() {return enabled;} private byte enabled = Bool_.__byte;
	public boolean Enabled_y() {return enabled == Bool_.Y_byte;}
	public boolean Enabled_n() {return enabled == Bool_.N_byte;}
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled))			return Yn.To_nullable_str(enabled);
		else if	(ctx.Match(k, Invk_enabled_))			enabled = Yn.To_nullable_byte(m.ReadStr("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_enabled = "enabled", Invk_enabled_ = "enabled_";
}
