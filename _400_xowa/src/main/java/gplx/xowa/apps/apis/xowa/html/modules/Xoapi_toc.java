package gplx.xowa.apps.apis.xowa.html.modules; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.html.*;
public class Xoapi_toc implements Gfo_invk {
	public void Init_by_kit(Xoae_app app) {
	}
	public boolean Collapsed() {return collapsed;} private boolean collapsed = false;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_collapsed))	 		return Yn.To_str(collapsed);
		else if	(ctx.Match(k, Invk_collapsed_))	 		collapsed = m.ReadYn("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_collapsed = "collapsed", Invk_collapsed_ = "collapsed_"
	;
}
