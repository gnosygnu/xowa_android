package gplx.xowa.apps.apis.xowa.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.bldrs.filters.*;
import gplx.xowa.apps.apis.xowa.bldrs.imports.*;
import gplx.xowa.apps.apis.xowa.bldrs.runners.*;
public class Xoapi_bldr_wiki implements GfoInvkAble {
	public void Ctor_by_app(Xoa_app app) {
		filter.Ctor_by_app(app);
		runner.Ctor_by_app(app);
	}
	public Xoapi_filter Filter() {return filter;} private final    Xoapi_filter filter = new Xoapi_filter();
	public Xoapi_import Import() {return import_api;} private final    Xoapi_import import_api = new Xoapi_import();
	public Xoapi_runner Runner() {return runner;} private final    Xoapi_runner runner = new Xoapi_runner();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_filter)) 						return filter;
		else if	(ctx.Match(k, Invk_import)) 						return import_api;
		else if	(ctx.Match(k, Invk_runner)) 						return runner;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_filter = "filter", Invk_import = "import", Invk_runner = "runner";
}
