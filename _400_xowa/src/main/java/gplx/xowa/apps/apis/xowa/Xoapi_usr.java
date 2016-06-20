package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.usrs.*;
public class Xoapi_usr implements Gfo_invk {
	public void Ctor_by_app(Xoae_app app) {
		bookmarks.Ctor_by_app(app);
		history.Ctor_by_app(app);
		logs.Ctor_by_app(app);
	}
	public void Init_by_app(Xoa_app app) {
		cache.Init_by_app(app);
	}
	public void Init_by_kit(Xoae_app app) {
		bookmarks.Init_by_kit(app);
		history.Init_by_kit(app);
	}
	public Xoapi_bookmarks	Bookmarks() {return bookmarks;} private final    Xoapi_bookmarks bookmarks = new Xoapi_bookmarks();
	public Xoapi_history	History()	{return history;}	private final    Xoapi_history history = new Xoapi_history();
	public Xoapi_logs		Logs()		{return logs;}		private final    Xoapi_logs logs = new Xoapi_logs();
	public Xoapi_cache		Cache()		{return cache;}		private final    Xoapi_cache cache = new Xoapi_cache();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_bookmarks)) 							return bookmarks;
		else if	(ctx.Match(k, Invk_history)) 							return history;
		else if	(ctx.Match(k, Invk_logs)) 								return logs;
		else if	(ctx.Match(k, Invk_cache)) 								return cache;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_bookmarks = "bookmarks", Invk_history = "history", Invk_logs = "logs", Invk_cache = "cache";
}
