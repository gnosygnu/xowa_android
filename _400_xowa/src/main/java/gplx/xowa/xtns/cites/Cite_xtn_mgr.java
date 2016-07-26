package gplx.xowa.xtns.cites; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Cite_xtn_mgr extends Xox_mgr_base {
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("cite");
	@Override public Xox_mgr Xtn_clone_new() {return new Cite_xtn_mgr();}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_group_default_name))		return String_.new_u8(group_default_name);
		else if	(ctx.Match(k, Invk_group_default_name_))	group_default_name = m.ReadBry("v");
		else	return super.Invk(ctx, ikey, k, m);
		return this;
	}
	private static final    String Invk_group_default_name = "group_default_name", Invk_group_default_name_ = "group_default_name_";

	public static byte[] Group_default_name() {return group_default_name;} private static byte[] group_default_name = Bry_.new_a7("lower-alpha");
}
