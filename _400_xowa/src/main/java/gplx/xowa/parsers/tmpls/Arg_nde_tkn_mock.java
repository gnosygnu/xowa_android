package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.parsers.miscs.*;
public class Arg_nde_tkn_mock extends Arg_nde_tkn {//#*inherit
	public Arg_nde_tkn_mock(boolean arg_key_is_int, String k, String v) {
		this.key_exists = !arg_key_is_int;
		if (key_exists)
			this.Key_tkn_(new Arg_itm_tkn_mock(k));
		this.Val_tkn_(new Arg_itm_tkn_mock(v));
	}
	public Arg_nde_tkn_mock(String k, String v) {
		key_exists = k != null;
		if (key_exists)
			this.Key_tkn_(new Arg_itm_tkn_mock(k));
		this.Val_tkn_(new Arg_itm_tkn_mock(v));
	}
	@Override public boolean KeyTkn_exists() {return key_exists;} private boolean key_exists;
}
class Arg_itm_tkn_mock extends Arg_itm_tkn_base {
	public Arg_itm_tkn_mock(String v) {
		byte[] dat_ary = Bry_.new_u8(v);
		this.Subs_add(new Xop_bry_tkn(-1, -1, dat_ary));
		this.Dat_ary_(dat_ary);
		this.val = v;
	}	String val;
	@Override public boolean Tmpl_evaluate(Xop_ctx ctx, byte[] src, Xot_invk caller, Bry_bfr bfr) {bfr.Add_str_u8(val); return true;}
}
