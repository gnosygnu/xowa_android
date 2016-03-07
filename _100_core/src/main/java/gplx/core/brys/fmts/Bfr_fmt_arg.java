package gplx.core.brys.fmts; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bfr_fmt_arg {
	public Bfr_fmt_arg(byte[] key, Bfr_arg arg) {this.Key = key; this.Arg = arg;}
	public byte[] Key;
	public Bfr_arg Arg;
	public static final Bfr_fmt_arg[] Ary_empty = new Bfr_fmt_arg[0];
}
