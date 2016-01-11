package gplx.xowa.xtns.math.texvcs.tkns; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
public class Texvc_regy_nde {
	private Texvc_tkn[] ary = Texvc_tkn_.Ary_empty; private int ary_max;
	public Texvc_tkn Get_at_or_fail(int uid) {
		Texvc_tkn rv = null;
		if (uid > - 1 && uid < ary_max) rv = ary[uid];
		if (rv == null) throw Err_.new_("math.texvc", "node token does not exist", "uid", uid);
		return rv;
	}
	public void Init(int expd_itms) {
		if (expd_itms > ary_max) {
			this.ary_max = expd_itms;
			this.ary = new Texvc_tkn[ary_max];
		}
		else {
			for (int i = 0; i < ary_max; ++i)
				ary[i] = null;
		}
	}
	public void Add(int idx, Texvc_tkn tkn) {
		if (idx >= ary_max) {
			int new_max = idx == 0 ? 2 : idx * 2;
			Texvc_tkn[] new_ary = new Texvc_tkn[new_max];
			for (int i = 0; i < ary_max; ++i)
				new_ary[i] = ary[i];
			this.ary = new_ary;
			this.ary_max = new_max;
		}
		ary[idx] = tkn;
	}
	public void Update_end(int uid, int end) {Get_at_or_fail(uid).Src_end_(end);}
}