package gplx.xowa.xtns.pfuncs.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_language extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_i18n_language;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_language().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self);
		Hash_adp_bry regy = Xol_lang_stub_.Regy();
		if (argx.length == 0) return;	// {{#language:}} should return ""; note that byte[0] will fail in Match_exact
		Object o = regy.Get_by_bry(argx);
		if (o == null)
			bfr.Add(argx);
		else {
			Xol_lang_stub lang_itm = (Xol_lang_stub)o;
			bfr.Add(lang_itm.Canonical_name());
		}
	}
}	
