package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.html.*;
public class Xoapi_html implements Gfo_invk {
	public void Ctor_by_app(Xoae_app app) {
		page.Ctor_by_app(app);
	}
	public void Init_by_kit(Xoae_app app) {
		tidy.Init_by_kit(app);
		modules.Init_by_kit(app);
	}
	public Xoapi_tidy		Tidy()		{return tidy;}		private final    Xoapi_tidy tidy = new Xoapi_tidy();
	public Xoapi_modules	Modules()	{return modules;}	private final    Xoapi_modules modules = new Xoapi_modules();
	public Xoapi_skins		Skins()		{return skins;}		private final    Xoapi_skins skins = new Xoapi_skins();
	public Xoapi_page		Page()		{return page;}		private final    Xoapi_page page = new Xoapi_page();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_tidy)) 						return tidy;
		else if	(ctx.Match(k, Invk_modules)) 					return modules;
		else if	(ctx.Match(k, Invk_skins)) 						return skins;
		else if	(ctx.Match(k, Invk_page)) 						return page;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_tidy = "tidy", Invk_modules = "modules", Invk_skins = "skins", Invk_page = "page";
}
