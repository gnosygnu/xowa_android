package gplx.xowa.xtns.wdatas.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_itemByTitle_cfg implements GfoInvkAble {
	public byte[] Site_default() {return site_default;} private byte[] site_default = Bry_.new_a7("enwiki");
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_site_default))		return site_default;
		else if (ctx.Match(k, Invk_site_default_))		site_default = m.ReadBry("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_site_default = "site_default", Invk_site_default_ = "site_default_";
	public static final String Key = "itemByTitle";
}
