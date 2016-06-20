package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.bldrs.*;
public class Xoapi_bldr implements Gfo_invk {
	public void Ctor_by_app(Xoa_app app) {wiki.Ctor_by_app(app);}
	public Xoapi_bldr_wiki		Wiki() {return wiki;} private final    Xoapi_bldr_wiki wiki = new Xoapi_bldr_wiki();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_wiki)) 									return wiki;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_wiki = "wiki";
}
