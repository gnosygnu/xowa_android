package gplx.xowa.wikis.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_user implements Gfo_invk {
	public byte[] Name() {return name;} private byte[] name = Bry_.new_a7("anonymous");
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_name_))						name = m.ReadBry("v");
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_name_ = "name_";
}
