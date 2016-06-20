package gplx.xowa.apps.apis.xowa.envs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
public class Xoapi_env implements Gfo_invk {
	public void Init_by_kit(Xoae_app app) {}
	public String Version_previous() {return version_previous;} private String version_previous = "";
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_version_previous)) 						return version_previous;
		else if	(ctx.Match(k, Invk_version_previous_)) 						version_previous = m.ReadStr("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_version_previous = "version_previous", Invk_version_previous_ = "version_previous_";
}
