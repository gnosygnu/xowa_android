package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.wikis.*;
public class Xoapi_app_wiki implements Gfo_invk {
	public Xoapi_wiki_lang	Lang()		{return lang;}		private final    Xoapi_wiki_lang lang = new Xoapi_wiki_lang();
	public Xoapi_hdump		Hdump()		{return hdump;}		private final    Xoapi_hdump hdump = new Xoapi_hdump();
	public void Subscribe(Gfo_evt_itm sub) {lang.Subscribe(sub);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_lang)) 						return lang;
		else if	(ctx.Match(k, Invk_hdump)) 						return hdump;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_lang = "lang", Invk_hdump = "hdump";
	public static final    Xoapi_app_wiki Dflt = new Xoapi_app_wiki();
}
