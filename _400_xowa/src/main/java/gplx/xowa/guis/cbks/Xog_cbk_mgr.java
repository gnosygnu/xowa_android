package gplx.xowa.guis.cbks; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.core.gfobjs.*;
public class Xog_cbk_mgr {	// INSTANCE:app
	private Xog_cbk_wkr[] wkrs = Xog_cbk_wkr_.Ary_empty; private int wkrs_len = 0;
	public void Reg(Xog_cbk_wkr wkr) {
		this.wkrs = (Xog_cbk_wkr[])Array_.Resize_add_one(wkrs, wkrs_len, wkr);
		++wkrs_len;
	}
	public void Send_json(Xog_cbk_trg trg, String func, Gfobj_nde data) {
		for (int i = 0; i < wkrs_len; ++i) {
			Xog_cbk_wkr wkr = wkrs[i];
			wkr.Send_json(trg, func, data);
		}
	}
}
