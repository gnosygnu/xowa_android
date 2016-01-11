package gplx.xowa.xtns.math.texvcs.tkns; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
import gplx.xowa.xtns.math.texvcs.funcs.*;
public class Texvc_tkn_mkr {
	private final Texvc_tkn[] regy = new Texvc_tkn[Texvc_tkn_.Tid_len + Texvc_func_itm_.Id_len];
	public void Reg_singleton(int tid, Texvc_tkn tkn) {
		regy[tid] = tkn;
	}
	public Texvc_tkn Get_singleton(Texvc_root root, int tid, int leaf_id, int uid, int bgn, int end) {
		Texvc_tkn singleton = regy[leaf_id];
		return singleton == null ? null : singleton.Init(root, tid, uid, bgn, end);
	}
	public static final int Singleton_id__null = -1;
}