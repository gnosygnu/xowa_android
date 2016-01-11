package gplx.xowa.xtns.math.texvcs.lxrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
import gplx.xowa.xtns.math.texvcs.tkns.*;
class Texvc_lxr__leaf implements Texvc_lxr {
	public Texvc_lxr__leaf(int tkn_tid) {this.tkn_tid = tkn_tid;} private final int tkn_tid;
	public int		Tid() {return Texvc_lxr_.Tid__raw;}
	public int		Make_tkn(Texvc_ctx ctx, Texvc_root root, byte[] src, int src_len, int bgn_pos, int cur_pos) {
		root.Regy__add(tkn_tid, tkn_tid, bgn_pos, cur_pos, null);
		return cur_pos;
	}
}
