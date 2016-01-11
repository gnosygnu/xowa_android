package gplx.xowa.xtns.math.texvcs.tkns; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
public class Texvc_tkn_stack {
	private Texvc_tkn[] ary; private int ary_len, ary_max;
	public Texvc_tkn_stack() {this.Clear();}
	public int Len() {return ary_len;}
	public void Clear() {
		this.ary = Texvc_tkn_.Ary_empty;
		this.ary_len = 0;
		this.ary_max = 0;
	}
	public void Add(Texvc_tkn tkn) {
		int new_ary_len = ary_len + 1;
		if (new_ary_len >= ary_max) {
			int new_max = ary_max == 0 ? 2 : ary_max * 2;
			Texvc_tkn[] new_ary = new Texvc_tkn[new_max];
			for (int i = 0; i < ary_len; ++i)
				new_ary[i] = ary[i];
			this.ary = new_ary;
			this.ary_max = new_max;
		}
		ary[ary_len] = tkn;
		this.ary_len = new_ary_len;
	}
	public Texvc_tkn Pop() {
		int new_ary_len = ary_len - 1;
		Texvc_tkn rv = ary[new_ary_len];
		this.ary_len = new_ary_len;
		return rv;
	}
}
