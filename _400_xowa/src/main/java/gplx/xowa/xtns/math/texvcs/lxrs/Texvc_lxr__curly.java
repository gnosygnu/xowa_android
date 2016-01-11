package gplx.xowa.xtns.math.texvcs.lxrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
import gplx.xowa.xtns.math.texvcs.tkns.*; import gplx.xowa.xtns.math.texvcs.funcs.*;
class Texvc_lxr__curly_bgn implements Texvc_lxr {
	public int		Tid() {return Texvc_lxr_.Tid__curly_bgn;}
	public int		Make_tkn(Texvc_ctx ctx, Texvc_root root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		int uid = root.Regy__add(Texvc_tkn_.Tid__curly, Texvc_tkn_mkr.Singleton_id__null, bgn_pos, cur_pos, new Texvc_tkn__func(Texvc_func_itm_.Itm__arg));
		ctx.Stack().Add(uid);
		return cur_pos;
	}
}
class Texvc_lxr__curly_end implements Texvc_lxr {
	public int		Tid() {return Texvc_lxr_.Tid__curly_end;}
	public int		Make_tkn(Texvc_ctx ctx, Texvc_root root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		int bgn_uid = ctx.Stack().Pop_or_fail();
		root.Regy__take_from_root_end(bgn_uid);
		root.Regy__update_end(bgn_uid, cur_pos);
		return cur_pos;
	}
}
