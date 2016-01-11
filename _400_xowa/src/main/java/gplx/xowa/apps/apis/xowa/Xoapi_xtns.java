package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.xtns.*;
public class Xoapi_xtns implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {
		scribunto.Init_by_kit(app);
	}
	public Xoapi_scribunto		Scribunto()		{return scribunto;} private final Xoapi_scribunto scribunto = new Xoapi_scribunto();
	public Xoapi_wikibase		Wikibase()		{return wikibase;}  private final Xoapi_wikibase  wikibase = new Xoapi_wikibase();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_scribunto))	 			return scribunto;
		else if	(ctx.Match(k, Invk_wikibase))	 			return wikibase;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String
	  Invk_scribunto = "scribunto"
	, Invk_wikibase  = "wikibase"
	;
}
