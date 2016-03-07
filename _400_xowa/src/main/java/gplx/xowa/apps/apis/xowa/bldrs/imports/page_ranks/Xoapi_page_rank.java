package gplx.xowa.apps.apis.xowa.bldrs.imports.page_ranks; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.bldrs.*; import gplx.xowa.apps.apis.xowa.bldrs.imports.*;
public class Xoapi_page_rank implements GfoInvkAble {
	public int		Iteration_max()	{return iteration_max;}	private int iteration_max = 0;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_iteration_max)) 							return Int_.To_str(iteration_max);
		else if	(ctx.Match(k, Invk_iteration_max_)) 						iteration_max = m.ReadInt("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_iteration_max		= "iteration_max"		, Invk_iteration_max_			= "iteration_max_"
	;
}
