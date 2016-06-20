package gplx.xowa.apps.apis.xowa.wikis.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.wikis.*;
public class Xoap_lang_variants implements Gfo_invk, Gfo_evt_mgr_owner {
	public Xoap_lang_variants() {
		this.ev_mgr = new Gfo_evt_mgr(this);
	}
	public Gfo_evt_mgr Evt_mgr() {return ev_mgr;} private final    Gfo_evt_mgr ev_mgr;
	public byte[] Current() {return current;} private byte[] current;
	public void Current_(byte[] v) {
		this.current = v;
		Gfo_evt_mgr_.Pub_val(this, Evt_current_changed, v);
	}
	public void Subscribe(Gfo_evt_itm sub) {
		Gfo_evt_mgr_.Sub_same(this, Evt_current_changed, sub);
		if (current != null) Gfo_invk_.Invk_by_val(sub, Evt_current_changed, current);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_current)) 							return String_.new_u8(current);
		else if	(ctx.Match(k, Invk_current_)) 							Current_(m.ReadBry("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_current = "current", Invk_current_ = "current_";
	public static final String
	  Evt_current_changed = "current_changed"
	;
}
