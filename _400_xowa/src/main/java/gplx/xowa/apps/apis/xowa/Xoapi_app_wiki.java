package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.wikis.*;
public class Xoapi_app_wiki implements GfoInvkAble {
	public Xoapi_wiki_lang	Lang()		{return lang;} private final Xoapi_wiki_lang lang = new Xoapi_wiki_lang();
	public void Subscribe(GfoEvObj sub) {lang.Subscribe(sub);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_lang)) 								return lang;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_lang = "lang";
	public static final Xoapi_app_wiki Dflt = new Xoapi_app_wiki();
}
