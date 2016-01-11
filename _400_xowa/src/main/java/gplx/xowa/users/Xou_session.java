package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xou_session implements GfoInvkAble {
	public Xou_session(Xoue_user user) {this.user = user; window_mgr = new Xous_window_mgr(user);}
	public Xoue_user User() {return user;} private Xoue_user user;
	public Xous_window_mgr Window_mgr() {return window_mgr;} private Xous_window_mgr window_mgr;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {			
		if		(ctx.Match(k, Invk_window))			return window_mgr;
		return this;
	}	public static final String Invk_window = "window";
}
