package gplx.xowa.bldrs.wms; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.xowa.bldrs.wms.apis.*; import gplx.xowa.wikis.*;	
public class Xoa_wmf_mgr implements GfoInvkAble {
	private final Xoae_wiki_mgr wiki_mgr;
	public Xoa_wmf_mgr(Gfo_usr_dlg usr_dlg, Xoae_wiki_mgr wiki_mgr) {
		this.wiki_mgr = wiki_mgr;
	}
	public boolean Enabled() {return enabled;} private boolean enabled = true; // default to true; DATE:2015-01-05
	public void Enabled_(boolean v) {
		enabled = v;
		int len = wiki_mgr.Count();
		for (int i = 0; i < len; i++) {
			Xowe_wiki wiki = wiki_mgr.Get_at_or_null(i);
			wiki.File_mgr().Cfg_download().Enabled_(v);
		}		
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled))			return Yn.To_str(enabled);
		else if	(ctx.Match(k, Invk_enabled_))			Enabled_(m.ReadYn("v"));
		else											return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_enabled = "enabled", Invk_enabled_ = "enabled_";
}
