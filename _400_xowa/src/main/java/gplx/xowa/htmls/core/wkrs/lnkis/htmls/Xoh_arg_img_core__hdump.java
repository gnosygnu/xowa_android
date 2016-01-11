package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
public class Xoh_arg_img_core__hdump implements gplx.core.brys.Bfr_arg, Xoh_arg_img_core {
	private int uid;
	public Xoh_arg_img_core Init(int uid, byte[] img_src, int img_w, int img_h) {
		this.uid = uid;
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add_byte_space();
		bfr.Add(gplx.xowa.htmls.core.makes.Xoh_make_trie_.Bry__img);
		bfr.Add_int_variable(uid);
		bfr.Add_byte_quote();
	}
}
