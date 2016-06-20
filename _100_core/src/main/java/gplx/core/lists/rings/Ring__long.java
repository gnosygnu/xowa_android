package gplx.core.lists.rings; import gplx.*; import gplx.core.*; import gplx.core.lists.*;
public class Ring__long {
	private final    long[] ary;
	private final    int max;
	private int nxt, idx_0;
	public Ring__long(int max) {
		this.max = max;
		this.ary = new long[max];
	}
	public int Len() {return len;} private int len;
	public void Clear() {
		for (int i = 0; i < max; ++i)
			ary[i] = 0;
		len = nxt = 0;
		idx_0 = 0;
	}
	public long Get_at(int i) {
		int idx = idx_0 + i;
		if (idx >= max) idx -= max;
		return ary[idx];
	}
	public void Add(long val) {			
		ary[nxt] = val;		// set ary idx
		if (++nxt == max)	// increment nxt; if max...
			nxt = 0;		// ...set to 0; 
		if (len == max)		// set idx_0
			idx_0 = nxt == 0 ? 0 : nxt;
		if (len < max)		// increment len unless already at max
			++len;
	}
	public long[] To_ary() {
		long[] rv = new long[len];
		for (int i = 0; i < len; ++i)
			rv[i] = Get_at(i);
		return rv;
	}
}
