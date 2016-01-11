package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
public class Xoa_special_mgr implements GfoInvkAble {
	private Ordered_hash hash = Ordered_hash_.New();
	public Xoa_special_mgr() {
		hash.Add(gplx.xowa.xtns.wdatas.specials.Wdata_itemByTitle_cfg.Key, new gplx.xowa.xtns.wdatas.specials.Wdata_itemByTitle_cfg());
	}
	public void Add(String key, GfoInvkAble cfg)	{hash.Add(key, cfg);}
	public GfoInvkAble Get_or_null(String key)		{return (GfoInvkAble)hash.Get_by(key);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))			return Get_or_null(m.ReadStr("v"));
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_get = "get";
}
