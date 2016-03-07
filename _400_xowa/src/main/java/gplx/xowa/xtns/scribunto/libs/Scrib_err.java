package gplx.xowa.xtns.scribunto.libs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
class Scrib_err {
	public static Err Make__err__arg(String proc, int arg_idx, String actl, String expd) {
		String rv = String_.Format("bad argument #{0} to {1} ({2} expected, got {3})", arg_idx, proc, expd, actl);	// "bad argument #$argIdx to '$name' ($expectType expected, got $type)"
		return Err_.new_("scribunto", rv);
	}
}
