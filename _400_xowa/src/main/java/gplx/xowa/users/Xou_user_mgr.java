package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xou_user_mgr implements Gfo_invk {
	public Xou_user_mgr(Xoae_app app, Xoue_user user) {this.app = app; this.Add(user);} private Xoae_app app;
	public void Add(Xoue_user itm) {regy.Add(itm.Key(), itm);}
	Xoue_user GetByKey(String key) {return (Xoue_user)regy.Get_by(key);}
	Ordered_hash regy = Ordered_hash_.New();

	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get)) {
			String user_key = m.ReadStr("key");
			Xoue_user user = GetByKey(user_key);
			if (user == null) {
				user = new Xoue_user(app, app.Fsys_mgr().Root_dir().GenSubDir_nest("user", user_key));
				this.Add(user);
			}
			return user;
		}
		else return Gfo_invk_.Rv_unhandled;
//			return this;
	}	private static final String Invk_get = "get";
}
