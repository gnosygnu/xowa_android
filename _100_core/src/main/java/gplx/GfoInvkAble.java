package gplx;
public interface GfoInvkAble {
	Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m);
}
/*
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_set)) {}
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_set = "set";
*/
