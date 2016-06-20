package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.cfgs.*;
public class Xoapi_toggle_mgr implements Gfo_invk {
	private Xoae_app app;
	private final    Ordered_hash hash = Ordered_hash_.New_bry();
	public void Ctor_by_app(Xoae_app app) {this.app = app;}
	public Xoapi_toggle_itm Get_or_new(String key_str) {
		byte[] key_bry = Bry_.new_u8(key_str);
		Xoapi_toggle_itm rv = (Xoapi_toggle_itm)hash.Get_by(key_bry);
		if (rv == null) {
			rv = new Xoapi_toggle_itm(app, key_bry);
			hash.Add(key_bry, rv);
		}
		return rv;
	}
	public void Img_dir_(Io_url v) {
		int len = hash.Count();
		for (int i = 0; i < len; ++i) {
			Xoapi_toggle_itm itm = (Xoapi_toggle_itm)hash.Get_at(i);
			itm.Init_fsys(v);
		}
	}
	public void Save(Xoa_cfg_mgr cfg_mgr) {
		int len = hash.Count();
		for (int i = 0; i < len; ++i) {
			Xoapi_toggle_itm itm = (Xoapi_toggle_itm)hash.Get_at(i);
			cfg_mgr.Set_by_app("xowa.api.html.page.toggles.get('" + String_.new_u8(itm.Key_bry()) + "').visible", Yn.To_str(itm.Visible()));
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get)) 			return this.Get_or_new(m.ReadStr("key"));
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_get = "get";
}
