package gplx.core.brys.fmts; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bry_fmt_itm {
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
