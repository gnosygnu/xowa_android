package gplx.xowa.htmls.portal; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public class Xoa_portal_mgr implements GfoInvkAble {
	public Xoa_portal_mgr(Xoae_app app) {wikis = new Xoa_available_wikis_mgr(app);}
	public Xoa_available_wikis_mgr Wikis() {return wikis;} private Xoa_available_wikis_mgr wikis;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_wikis))				return wikis;
		else return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_wikis = "wikis";
}
