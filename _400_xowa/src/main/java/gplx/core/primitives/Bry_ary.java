package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Bry_ary {
	private byte[][] ary; private int len, max;
	public Bry_ary(int max) {
		this.len = 0;
		this.max = max;
		this.ary = new byte[max][];
	}
	public byte[][] Ary() {return ary;}
	public void Clear() {
		for (int i = 0; i < len; ++i)
			ary[i] = null;
		len = 0;
	}
	public int Len() {return len;}
	public void Add(byte[] v) {
		if (len == max) {
			int new_max = max * 2;
			byte[][] new_ary = new byte[new_max][];
			for (int i = 0; i < len; ++i)
				new_ary[i] = ary[i];
			this.ary = new_ary;
			this.max = new_max;
		}
		ary[len] = v;
		++len;
	}
	public byte[] Get_at(int i) {return ary[i];}
	public byte[] Get_at_last() {return len == 0 ? null : ary[len - 1];}
	public void Set_at_last(byte[] v)   {ary[len - 1] = v;}
	public void Set_at(int i, byte[] v) {ary[i] = v;}
	public byte[][] To_ary(int rel) {
		if (len == 0) return Bry_.Ary_empty;
		int rv_len = len + rel;
		byte[][] rv = new byte[rv_len][];
		for (int i = 0;  i < rv_len; ++i)
			rv[i] = ary[i];
		return rv;
	}
}
