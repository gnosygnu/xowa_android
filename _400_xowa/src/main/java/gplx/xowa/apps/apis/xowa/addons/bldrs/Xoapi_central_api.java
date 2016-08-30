package gplx.xowa.apps.apis.xowa.addons.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.addons.*;
public class Xoapi_central_api implements Gfo_invk {
	public boolean		Log_verbose()		{return log_verbose;}		private boolean log_verbose = false;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__log_verbose_)) 						log_verbose = m.ReadBool("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk__log_verbose_				= "log_verbose_"
	;
}
