package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
public class Xoapi_app_wikis implements GfoInvkAble {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public Xoapi_app_wiki Get(byte[] domain) {
		Xoapi_app_wiki rv = (Xoapi_app_wiki)hash.Get_by(domain);
		if (rv == null) {
			rv = new Xoapi_app_wiki();
			hash.Add(domain, rv);
		}
		return rv;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get)) 								return Get(m.ReadBry("v"));
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_get = "get";
}
