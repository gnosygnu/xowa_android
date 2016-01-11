package gplx;
import gplx.core.primitives.*;
public class GfoInvkCmdMgr {
	public GfoInvkCmdMgr Add_cmd_many(GfoInvkAble invk, String... keys) {
		for (String key : keys)
			list.Add(GfoInvkCmdItm.new_(key, invk));
		return this;
	}
	public GfoInvkCmdMgr Add_cmd(String key, GfoInvkAble invk) {
		list.Add(GfoInvkCmdItm.new_(key, invk));
		return this;
	}
	public GfoInvkCmdMgr Add_mgr(String key, GfoInvkAble invk) {
		list.Add(GfoInvkCmdItm.new_(key, invk).Type_isMgr_(true));
		return this;
	}
	public GfoInvkCmdMgr Add_xtn(GfoInvkAble xtn) {
		list.Add(GfoInvkCmdItm.new_("xtn", xtn).Type_isXtn_(true));
		return this;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m, Object host) {
		for (int i = 0; i < list.Count(); i++) {
			GfoInvkCmdItm itm = (GfoInvkCmdItm)list.Get_at(i);
			if (itm.Type_isXtn()) {
				Object invkVal = itm.Invk().Invk(ctx, ikey, k, m);
				if (invkVal != GfoInvkAble_.Rv_unhandled) return invkVal;
			}
			if (!ctx.Match(k, itm.Key())) continue;
			if (itm.Type_isMgr()) return itm.Invk();
			Object rv = null;
			m.Add("host", host);
			rv = itm.Invk().Invk(ctx, ikey, k, m);
			return rv == GfoInvkAble_.Rv_host ? host : rv;	// if returning "this" return host
		}
		return Unhandled;
	}
	public static final String_obj_val Unhandled = String_obj_val.new_("GfoInvkCmdMgr Unhandled");
	List_adp list = List_adp_.new_();
        public static GfoInvkCmdMgr new_() {return new GfoInvkCmdMgr();} GfoInvkCmdMgr() {}
}
class GfoInvkCmdItm {
	public String Key() {return key;} private String key;
	public GfoInvkAble Invk() {return invk;} GfoInvkAble invk;
	public boolean Type_isMgr() {return type_isMgr;} public GfoInvkCmdItm Type_isMgr_(boolean v) {type_isMgr = v; return this;} private boolean type_isMgr;
	public boolean Type_isXtn() {return type_isXtn;} public GfoInvkCmdItm Type_isXtn_(boolean v) {type_isXtn = v; return this;} private boolean type_isXtn;
        public static GfoInvkCmdItm new_(String key, GfoInvkAble invk) {
		GfoInvkCmdItm rv = new GfoInvkCmdItm();
		rv.key = key; rv.invk = invk;
		return rv;
	}	GfoInvkCmdItm() {}
}
