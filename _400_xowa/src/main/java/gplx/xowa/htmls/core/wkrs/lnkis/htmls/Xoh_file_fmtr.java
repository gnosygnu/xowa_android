package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.xowa.files.*; import gplx.xowa.htmls.core.htmls.*;
public interface Xoh_file_fmtr {
	void Add_full_img(Bry_bfr bfr, Xoh_wtr_ctx hctx, Xoae_page page, byte[] src, Xof_file_itm xfer_itm, int uid
		, byte[] a_href, boolean a_href_is_file, byte a_cls, byte a_rel, byte[] a_title, byte[] a_xowa_title
		, int img_w, int img_h, byte[] img_src, byte[] img_alt, byte img_cls, byte[] img_cls_other			
		);
}
