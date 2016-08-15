package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
import gplx.xowa.xtns.pfuncs.*;
public class Lst_pfunc_lsth extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_lsth;}
	@Override public Pf_func New(int id, byte[] name) {return new Lst_pfunc_lsth().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		// get args
		byte[] page_ttl = Eval_argx(ctx, src, caller, self); if (Bry_.Len_eq_0(page_ttl)) return;		// {{#lsth:}} -> ""
		int args_len = self.Args_len();
		byte[] hdr_bgn = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 0, Lst_pfunc_itm_.Null_arg);
		byte[] hdr_end = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 1, Lst_pfunc_itm_.Null_arg);

		// parse
		Lst_pfunc_itm itm = Lst_pfunc_itm.New_hdr_or_null(ctx, page_ttl); if (itm == null) return;
		Lst_pfunc_itm_.Hdr_include(bfr, itm.Itm_src(), itm.Toc_mgr(), hdr_bgn, hdr_end);
	}
	public static final    Lst_pfunc_lsth Prime = new Lst_pfunc_lsth(); Lst_pfunc_lsth() {}
}
