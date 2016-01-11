package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public class Xowc_xtns implements GfoInvkAble {
	private Hash_adp_bry hash = Hash_adp_bry.ci_a7();
	public Xowc_xtns() {hash.Add(Xowc_xtn_pages.Xtn_key, itm_pages);}
	public Xowc_xtn_pages Itm_pages() {return itm_pages;} private Xowc_xtn_pages itm_pages = new Xowc_xtn_pages();
	public Object Get_by_key(byte[] key) {return hash.Get_by_bry(key);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))					return (GfoInvkAble)hash.Get_by_bry(m.ReadBry("v"));
		else return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_get = "get";
}
