package gplx.xowa.wikis.pages.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_xtn_skin_fmtr_arg implements gplx.core.brys.Bfr_arg {
	private Xoae_page page; private byte xtn_skin_tid;
	public Xopg_xtn_skin_fmtr_arg(Xoae_page page, byte xtn_skin_tid) {
		this.page = page; this.xtn_skin_tid = xtn_skin_tid;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		Xopg_xtn_skin_mgr mgr = page.Html_data().Xtn_skin_mgr();
		int len = mgr.Count();
		for (int i = 0; i < len; ++i) {
			Xopg_xtn_skin_itm itm = mgr.Get_at(i);
			if (itm.Tid() == xtn_skin_tid)
				itm.Write(bfr, page);
		}
	}
}
