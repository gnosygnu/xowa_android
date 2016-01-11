package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.specials.*;
public class Xoapi_special implements GfoInvkAble {
	public void Ctor_by_app(Xoae_app app) {
	}
	public void Init_by_kit(Xoae_app app) {
	}
	public Xoapi_search		Search()	{return search;}	private Xoapi_search search = new Xoapi_search();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_search)) 							return search;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_search = "search";
}
