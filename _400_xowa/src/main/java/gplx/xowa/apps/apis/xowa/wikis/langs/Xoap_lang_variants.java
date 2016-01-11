package gplx.xowa.apps.apis.xowa.wikis.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.wikis.*;
public class Xoap_lang_variants implements GfoInvkAble, GfoEvMgrOwner {
	public Xoap_lang_variants() {
		this.ev_mgr = GfoEvMgr.new_(this);
	}
	public GfoEvMgr EvMgr() {return ev_mgr;} private final GfoEvMgr ev_mgr;
	public byte[] Current() {return current;} private byte[] current;
	public void Current_(byte[] v) {
		this.current = v;
		GfoEvMgr_.PubVal(this, Evt_current_changed, v);
	}
	public void Subscribe(GfoEvObj sub) {
		GfoEvMgr_.SubSame(this, Evt_current_changed, sub);
		if (current != null) GfoInvkAble_.InvkCmd_val(sub, Evt_current_changed, current);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_current)) 							return String_.new_u8(current);
		else if	(ctx.Match(k, Invk_current_)) 							Current_(m.ReadBry("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_current = "current", Invk_current_ = "current_";
	public static final String
	  Evt_current_changed = "current_changed"
	;
}
