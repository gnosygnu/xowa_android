package gplx.xowa.apps.apis.xowa.addons; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.domains.crts.*;
import gplx.xowa.apps.apis.xowa.addons.searchs.*;
public class Xoapi_search_addon implements GfoInvkAble {
	public Xoapi_search_addon() {}
	public Xoapi_url_bar		Url_bar()		{return url_bar;}		private final    Xoapi_url_bar url_bar = new Xoapi_url_bar();
	@gplx.Internal protected Xoapi_search_box		Search_box()	{return search_box;}	private final    Xoapi_search_box search_box = new Xoapi_search_box();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__url_bar)) 						return url_bar;
		else if	(ctx.Match(k, Invk__search_box)) 					return search_box;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String
	  Invk__url_bar				= "url_bar"
	, Invk__search_box			= "search_box"
	;
}
class Xoapi_search_box implements GfoInvkAble {
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		return null;
	}
}
