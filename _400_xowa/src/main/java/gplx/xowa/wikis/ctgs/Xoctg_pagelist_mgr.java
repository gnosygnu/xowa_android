package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.core.htmls.*;
public class Xoctg_pagelist_mgr implements gplx.core.brys.Bfr_arg {
	public Xoctg_pagelist_mgr Init_by_app(Xoae_app app, Xoctg_pagelist_wtr hidden_wtr) {
		this.fmtr_all = hidden_wtr.Fmtr_all();
		grp_normal.Init_app(app, Bool_.Y, hidden_wtr.Fmtr_grp_normal(), hidden_wtr.Fmtr_itm());
		grp_hidden.Init_app(app, Bool_.N, hidden_wtr.Fmtr_grp_hidden(), hidden_wtr.Fmtr_itm());
		return this;
	}	private Bry_fmtr fmtr_all;
	public void Init_by_wiki(Xowe_wiki wiki, Xoh_wtr_ctx hctx) {
		grp_normal.Init_by_wiki(wiki, hctx);
		grp_hidden.Init_by_wiki(wiki, hctx);
	}
	public Xoctg_pagelist_grp Grp_normal() {return grp_normal;} private Xoctg_pagelist_grp grp_normal = new Xoctg_pagelist_grp();
	public Xoctg_pagelist_grp Grp_hidden() {return grp_hidden;} private Xoctg_pagelist_grp grp_hidden = new Xoctg_pagelist_grp();
	public void Bfr_arg__add(Bry_bfr bfr) {
		fmtr_all.Bld_bfr_many(bfr, grp_normal, grp_hidden);
	}
}
