package gplx.xowa.apps.apis.xowa.bldrs.filters; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.bldrs.*;
import gplx.xowa.apps.apis.xowa.bldrs.filters.dansguardians.*;
public class Xoapi_filter implements Gfo_invk {
	public void Ctor_by_app(Xoa_app app) {dansguardian.Ctor_by_app(app);}
	public Xoapi_dansguardian Dansguardian() {return dansguardian;} private final    Xoapi_dansguardian dansguardian = new Xoapi_dansguardian();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_dansguardian)) 						return dansguardian;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_dansguardian = "dansguardian";
}
