package gplx.core.strings; import gplx.*; import gplx.core.*;
public class String_ring {
	String[] ary = String_.Ary_empty;
	public int Len() {return len;} int len;
	public String_ring Max_(int v) {
		if (v != max) {
			ary = new String[v];
			max = v;
		}
		return this;
	} int max;
	public void Clear() {
		for (int i = 0; i < max; i++) {
			ary[i] = null;
		}
		len = nxt = 0;
	}
	int nxt;
	public void Push(String v) {
		int idx = nxt++;
		if (idx == max) {
			idx = 0;
		}
		if (nxt == max) {
			nxt = 0;
		}
		ary[idx] = v;
		if (len < max)
			++len;
	}
	public String[] Xto_str_ary() {
		String[] rv = new String[len];
		int ary_i = nxt - 1;
		for (int rv_i = len - 1; rv_i > -1; rv_i--) {
			if (ary_i == -1) {
				ary_i = max - 1;
			} 
			rv[rv_i] = ary[ary_i];
			--ary_i;
		}
		return rv;
	}
//	public String Get_at(int i) {return }
}
