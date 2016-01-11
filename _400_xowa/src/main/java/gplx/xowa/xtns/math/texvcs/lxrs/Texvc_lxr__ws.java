package gplx.xowa.xtns.math.texvcs.lxrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
import gplx.xowa.xtns.math.texvcs.tkns.*;
class Texvc_lxr__ws implements Texvc_lxr {
	public int		Tid() {return Texvc_lxr_.Tid__ws;}
	public int		Make_tkn(Texvc_ctx ctx, Texvc_root root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		while (true) {
			if (cur_pos == src_len) break;
			if (src[cur_pos] != Byte_ascii.Space)
				break;
			++cur_pos;
		}
		root.Regy__add(Texvc_tkn_.Tid__ws, Texvc_tkn_.Tid__ws, bgn_pos, cur_pos, null);
		return cur_pos;
	}
}
