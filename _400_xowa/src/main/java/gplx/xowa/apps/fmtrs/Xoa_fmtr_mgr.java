package gplx.xowa.apps.fmtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.core.ios.*;
public class Xoa_fmtr_mgr implements Gfo_invk {
	public Xoa_fmtr_mgr(Xoae_app app) {this.app = app;} private Xoae_app app;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_new_grp))		return new Xoa_fmtr_itm(app);
		else return Gfo_invk_.Rv_unhandled;
	}	private static final    String Invk_new_grp = "new_grp";
}
