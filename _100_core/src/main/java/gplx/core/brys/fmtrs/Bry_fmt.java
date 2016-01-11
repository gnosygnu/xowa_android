package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bry_fmt {
	private byte[] src;
	private Bry_fmt_itm[] itms; private int itms_len;
	private Bfr_fmt_arg[] args = Bfr_fmt_arg.Ary_empty;
	private byte[][] keys = Bry_.Ary_empty;
	private boolean dirty;
	public Bry_fmt(byte[] src, byte[][] keys, Bfr_fmt_arg[] args) {
		dirty = true;
		this.src = src; this.keys = keys; this.args = args;
	}
	public Bry_fmt Fmt_(String v)						{dirty = true; src = Bry_.new_u8(v); return this;}
	public Bry_fmt Args_(Bfr_fmt_arg... v)		{dirty = true; args = v; return this;}
	public Bry_fmt Keys_(byte[]... v)				{dirty = true; keys = v; return this;}
	public void Bld_bfr_many(Bry_bfr bfr, Object... vals) {
		if (dirty) Compile();
		int vals_len = vals.length;
		for (int i = 0; i < itms_len; ++i) {
			Bry_fmt_itm itm = itms[i];
			switch (itm.Tid) {
				case Bry_fmt_itm.Tid__txt: bfr.Add_mid(src, itm.Src_bgn, itm.Src_end); break;
				case Bry_fmt_itm.Tid__arg: itm.Arg.Bfr_arg__add(bfr);break;
				case Bry_fmt_itm.Tid__key: 
					int idx = itm.Key_idx;
					if (idx > -1 && idx < vals_len)
						bfr.Add_obj(vals[idx]);
					else
						bfr.Add_mid(src, itm.Src_bgn, itm.Src_end);
					break;
				default: throw Err_.new_unhandled(itm.Tid);
			}
		}
	}
	private void Compile() {
		dirty = false;
		this.itms = Bry_fmt_parser_.Parse(Byte_ascii.Tilde, Byte_ascii.Curly_bgn, Byte_ascii.Curly_end, args, keys, src);
		this.itms_len = itms.length;
	}
	public static Bry_fmt New(String fmt, String... keys) {return new Bry_fmt(Bry_.new_u8(fmt), Bry_.Ary(keys), Bfr_fmt_arg.Ary_empty);}
}
class Bry_fmt_itm {
	public Bry_fmt_itm(int tid, int src_bgn, int src_end) {
		this.Tid = tid;
		this.Src_bgn = src_bgn;
		this.Src_end = src_end;
	}
	public int		Tid;
	public int		Src_bgn;
	public int		Src_end;
	public int		Key_idx;
	public Bfr_arg	Arg;

	public static final int Tid__txt = 0, Tid__key = 1, Tid__arg = 2;
}
