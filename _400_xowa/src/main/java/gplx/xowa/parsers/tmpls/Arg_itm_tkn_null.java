package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.htmls.*;
public class Arg_itm_tkn_null extends Xop_tkn_null implements Arg_itm_tkn {//#*inherit
	public int Dat_bgn() {return -1;}
	public int Dat_end() {return -1;} public Arg_itm_tkn Dat_end_(int v) {return this;}
	public Arg_itm_tkn Dat_rng_(int bgn, int end) {return this;}
	public Arg_itm_tkn Dat_rng_ary_(byte[] src, int bgn, int end) {return this;}
	public byte[] Dat_ary() {return Bry_.Empty;} public Arg_itm_tkn Dat_ary_(byte[] dat_ary) {return this;}
	public byte[] Dat_to_bry(byte[] src) {return Bry_.Empty;}
	public Arg_itm_tkn Subs_add_ary(Xop_tkn_itm... ary) {return this;}
	public boolean Dat_ary_had_subst() {return false;} public void Dat_ary_had_subst_y_() {}
	public byte Itm_static() {return Bool_.__byte;} public Arg_itm_tkn Itm_static_(boolean v) {return this;}
	public static final Arg_itm_tkn_null Null_arg_itm = new Arg_itm_tkn_null(); Arg_itm_tkn_null() {}
}
