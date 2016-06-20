package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.wikis.*;
public class Xoapi_wiki implements Gfo_invk {
	public Xoapi_hdump		Hdump()		{return hdump;}		private final    Xoapi_hdump hdump = new Xoapi_hdump();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_hdump)) 						return hdump;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_hdump = "hdump";
}
