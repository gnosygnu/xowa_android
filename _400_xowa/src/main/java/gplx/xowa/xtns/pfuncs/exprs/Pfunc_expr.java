package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*; import gplx.core.log_msgs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_expr extends Pf_func_base {
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] val_dat_ary = Eval_argx(ctx, src, caller, self); if (val_dat_ary == Bry_.Empty) return;
		Evaluate(bfr, ctx, val_dat_ary);
	}
	public static boolean Evaluate(Bry_bfr bfr, Xop_ctx ctx, byte[] expr) {
		Decimal_adp rslt = shunter.Evaluate(ctx, expr);	// NOTE: php uses "float" but really is a double; http://www.php.net/manual/en/language.types.float.php
		if (rslt == Pfunc_expr_shunter.Null_rslt) {
			bfr.Add_bfr_and_preserve(shunter.Err());
			shunter.Err().Clear();
			return false;
		}
		else {
			bfr.Add_str_u8(rslt.To_str());
			return true;
		}
	}
	private static Pfunc_expr_shunter shunter = Pfunc_expr_shunter.Instance;
	@Override public int Id() {return Xol_kwd_grp_.Id_xtn_expr;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_expr().Name_(name);}
}
class Pfunc_expr_msg {
	public static final Gfo_msg_grp Nde = Gfo_msg_grp_.new_(Xoa_app_.Nde, "expr");
	public static final Gfo_msg_itm Unknown = Gfo_msg_itm_.new_warn_(Nde, "unknown", "unknown");
}
