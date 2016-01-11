package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*;
public class Xoctg_pagelist_grp implements gplx.core.brys.Bfr_arg {
	public void Init_app(Xoae_app app, boolean type_is_normal, Bry_fmtr fmtr_grp, Bry_fmtr fmtr_itm) {
		this.type_is_normal = type_is_normal;
		this.fmtr_grp = fmtr_grp;
		itms.Init_app(app, fmtr_itm);
	}	private Bry_fmtr fmtr_grp;
	public void Init_by_wiki(Xowe_wiki wiki, Xoh_wtr_ctx hctx) {
		lbl_ctg_text	= wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_ctg_tbl_hdr);
		lbl_ctg_help	= Xol_msg_mgr_.Get_msg_val(wiki, wiki.Lang(), Key_pagecategorieslink, Bry_.Ary_empty);
		lbl_hidden		= wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_ctg_tbl_hidden);
		itms.Init_wiki(wiki, hctx);
	}	private byte[] lbl_ctg_help, lbl_ctg_text, lbl_hidden; private static final byte[] Key_pagecategorieslink = Bry_.new_a7("pagecategorieslink");
	public boolean Type_is_normal() {return type_is_normal;} private boolean type_is_normal;
	public Xoctg_pagelist_itms Itms() {return itms;} private Xoctg_pagelist_itms itms = new Xoctg_pagelist_itms();
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (type_is_normal)
			fmtr_grp.Bld_bfr_many(bfr, lbl_ctg_help, lbl_ctg_text, itms);
		else
			fmtr_grp.Bld_bfr_many(bfr, lbl_hidden, itms);
	}
}
