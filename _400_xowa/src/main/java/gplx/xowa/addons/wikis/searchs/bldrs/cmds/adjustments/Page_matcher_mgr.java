package gplx.xowa.addons.wikis.searchs.bldrs.cmds.adjustments; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.searchs.*; import gplx.xowa.addons.wikis.searchs.bldrs.*; import gplx.xowa.addons.wikis.searchs.bldrs.cmds.*;
import gplx.core.lists.hashs.*; import gplx.core.primitives.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.tbls.*;
class Page_matcher_mgr implements Gfo_invk {
	private final    Xow_wiki wiki;
	public Page_matcher_mgr(Xow_wiki wiki) {this.wiki = wiki;}
	private final    Hash_adp__int hash = new Hash_adp__int();
	public Page_matcher_wkr Get_by(int ns_id) {
		Page_matcher_wkr rv = (Page_matcher_wkr)hash.Get_by_or_null(ns_id);
		if (rv == null) {
			rv = new Page_matcher_wkr(wiki, ns_id);
			hash.Add(ns_id, rv);
		}
		return rv;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__get))		return Get_by(m.ReadInt("v"));
		else	return Gfo_invk_.Rv_unhandled;
	}	private static final String Invk__get = "get";
}
