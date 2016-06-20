package gplx.xowa.apps.apis.xowa.apps; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.core.envs.*;
public class Xoapi_fsys implements Gfo_invk {
	public void Ctor_by_app(Xoae_app app) {
		this.plat_jar = Env_.AppUrl();
		this.root_dir = app.Fsys_mgr().Root_dir();
	}
	public Io_url			Plat_jar()				{return plat_jar;}	private Io_url plat_jar;
	public Io_url			Plat_url(String s)		{return Io_url_.new_any_(root_dir.Gen_sub_path_for_os(s));} private Io_url root_dir;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_plat_jar)) 							return plat_jar;
		else if	(ctx.Match(k, Invk_plat_url)) 							return Plat_url(m.ReadStr("v"));
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_plat_jar = "plat_jar", Invk_plat_url = "plat_url";
}
