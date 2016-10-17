package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.addons.*;
public class Xoapi_addon implements Gfo_invk {
	public void Ctor_by_app(Xoa_app app) {}
	public Xoapi_addon_search		Search()	{return search;}	private final    Xoapi_addon_search search = new Xoapi_addon_search();
	public Xoapi_addon_bldr			Bldr()		{return bldr;}		private final    Xoapi_addon_bldr bldr = new Xoapi_addon_bldr();
	public boolean						Wikis__ctgs__hidden_enabled() {return wikis__ctgs__hidden_enabled;} private boolean wikis__ctgs__hidden_enabled = false;
	public boolean						App__scripting__enabled() {return app__scripting__enabled;} private boolean app__scripting__enabled = false;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__search)) 								return search;
		else if	(ctx.Match(k, Invk__bldr)) 									return bldr;
		else if	(ctx.Match(k, Invk__wikis__ctgs__hidden_enabled)) 			return Yn.To_str(wikis__ctgs__hidden_enabled);
		else if	(ctx.Match(k, Invk__wikis__ctgs__hidden_enabled_)) 			wikis__ctgs__hidden_enabled = m.ReadYn("v");
		else if	(ctx.Match(k, Invk__app__scripting__enabled)) 				return Yn.To_str(app__scripting__enabled);
		else if	(ctx.Match(k, Invk__app__scripting__enabled_)) 				app__scripting__enabled = m.ReadYn("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk__search = "search", Invk__bldr = "bldr"
	, Invk__wikis__ctgs__hidden_enabled		= "wikis__ctgs__hidden_enabled"
	, Invk__wikis__ctgs__hidden_enabled_	= "wikis__ctgs__hidden_enabled_"
	, Invk__app__scripting__enabled			= "app__scripting__enabled"
	, Invk__app__scripting__enabled_		= "app__scripting__enabled_"
	;
}
