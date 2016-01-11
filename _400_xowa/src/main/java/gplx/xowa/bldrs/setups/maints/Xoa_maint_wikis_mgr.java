package gplx.xowa.bldrs.setups.maints; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.setups.*;
public class Xoa_maint_wikis_mgr implements GfoInvkAble {
	public Xoa_maint_wikis_mgr(Xoae_app app) {this.app = app;} private Xoae_app app;
	private Ordered_hash hash = Ordered_hash_.New_bry();
	public int Len() {return hash.Count();}
	public Xowe_wiki Get_at(int i) {
		if (init) Init();
		byte[] domain = (byte[])hash.Get_at(i);
		Xowe_wiki wiki = app.Wiki_mgr().Get_by_key_or_make(domain);
		wiki.Init_assert();
		return wiki;
	}
	public void Add(byte[] domain) {hash.Add_if_dupe_use_nth(domain, domain);}	// NOTE: must be Add_if_dupe_use_nth to replace existing wikis
	public void Init() {
		int len = this.Len();
		for (int i = 0; i < len; i++) {
			byte[] domain = (byte[])hash.Get_at(i);
			Xowe_wiki wiki = app.Wiki_mgr().Get_by_key_or_make(domain);
			wiki.Init_assert();
		}
		init = false;
	}
	private boolean init = true;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_len))		return this.Len();
		else if	(ctx.Match(k, Invk_get_at))		return this.Get_at(m.ReadInt("v"));
		else	return GfoInvkAble_.Rv_unhandled;
//			return this;
	}	private static final String Invk_len = "len", Invk_get_at = "get_at";
}