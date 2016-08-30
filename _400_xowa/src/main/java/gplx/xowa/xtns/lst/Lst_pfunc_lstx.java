package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Lst_pfunc_lstx extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_lstx;}
	@Override public Pf_func New(int id, byte[] name) {return new Lst_pfunc_lstx().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] page_ttl = Eval_argx(ctx, src, caller, self); if (Bry_.Len_eq_0(page_ttl)) return;		// {{#lst:}} -> ""
		int args_len = self.Args_len();
		byte[] sect_exclude = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 0, Lst_pfunc_itm.Null_arg);
		byte[] sect_replace = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 1, Lst_pfunc_itm.Null_arg);

		// parse
		Lst_pfunc_itm itm = Lst_pfunc_itm.New_sect_or_null(ctx, page_ttl); if (itm == null) return;
		Lst_pfunc_lstx_.Sect_exclude(bfr, itm.Sec_mgr(), itm.Itm_src(), sect_exclude, sect_replace);
	}
	public static final    Lst_pfunc_lstx Prime = new Lst_pfunc_lstx(); Lst_pfunc_lstx() {}
}
