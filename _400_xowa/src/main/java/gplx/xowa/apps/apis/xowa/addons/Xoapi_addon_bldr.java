package gplx.xowa.apps.apis.xowa.addons; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.addons.bldrs.*;
public class Xoapi_addon_bldr implements Gfo_invk {
	public Xoapi_central_api		Central()		{return central;}		private final    Xoapi_central_api central = new Xoapi_central_api();
	public Xoapi_sync_api			Sync()			{return sync;}			private final    Xoapi_sync_api sync = new Xoapi_sync_api();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__central)) 						return central;
		else if	(ctx.Match(k, Invk__sync)) 							return sync;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String
	  Invk__central				= "central"
	, Invk__sync				= "sync"
	;
}
