package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Lst_pfunc_lst extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_lst;}
	@Override public Pf_func New(int id, byte[] name) {return new Lst_pfunc_lst().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		// get args
		byte[] page_ttl = Eval_argx(ctx, src, caller, self); if (Bry_.Len_eq_0(page_ttl)) return;		// {{#lst:}} -> ""
		int args_len = self.Args_len();
		byte[] sect_bgn = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 0, Lst_pfunc_itm_.Null_arg);
		byte[] sect_end = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 1, Lst_pfunc_itm_.Null_arg);
		
		// parse
		Lst_pfunc_itm itm = Lst_pfunc_itm.New_or_null(ctx, page_ttl); if (itm == null) return;
		Lst_pfunc_itm_.Sect_include(bfr, itm.Itm_ctx(), itm.Itm_src(), sect_bgn, sect_end);
	}


	public static final    Lst_pfunc_lst Prime = new Lst_pfunc_lst(); Lst_pfunc_lst() {}
}
