package gplx.xowa.htmls.core.hzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.core.btries.*; import gplx.core.threads.poolables.*;
import gplx.xowa.htmls.core.wkrs.escapes.*;
import gplx.xowa.htmls.core.wkrs.hdrs.*; import gplx.xowa.htmls.core.wkrs.lnkes.*; import gplx.xowa.htmls.core.wkrs.lnkis.*; import gplx.xowa.htmls.core.wkrs.xndes.*;
import gplx.xowa.htmls.core.wkrs.imgs.*; import gplx.xowa.htmls.core.wkrs.thms.*; import gplx.xowa.htmls.core.wkrs.glys.*;
public class Xoh_pool_mgr__data {
	private final Gfo_poolable_mgr pool__hdr, pool__lnke, pool__img, pool__img_bare;//, pool__gly;
	public Xoh_pool_mgr__data() {
		pool__hdr		= New_pool(new Xoh_hdr_data());
		pool__lnke		= New_pool(new Xoh_lnke_data());
		pool__img		= New_pool(new Xoh_img_data());
		pool__img_bare	= New_pool(new Xoh_img_bare_data());
//			pool__gly	= New_pool(new Xoh_gly_grp_data());
	}
	public Xoh_data_itm Get_by_tid(int tid) {
		Gfo_poolable_mgr pool = null;
		switch (tid) {
			case Xoh_hzip_dict_.Tid__hdr:		pool = pool__hdr; break;
			case Xoh_hzip_dict_.Tid__lnke:		pool = pool__lnke; break;
			case Xoh_hzip_dict_.Tid__img:		pool = pool__img; break;
			case Xoh_hzip_dict_.Tid__img_bare:	pool = pool__img_bare; break;
//				case Xoh_hzip_dict_.Tid__gly:		pool = pool__gly; break;
			default:							return null;
		}
		return (Xoh_data_itm)pool.Get_fast();
	}
	private static Gfo_poolable_mgr New_pool(Gfo_poolable_itm proto) {
		return Gfo_poolable_mgr_.New(1, 32, proto);
	}
}
