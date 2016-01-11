package gplx.xowa.xtns.pfuncs.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_grammar extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_i18n_grammar;}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_grammar().Name_(name);}	// NOTE: odmiana used as magic-word / template in pl.d; EX:pl.d:hund; DATE:2014-08-14
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self);
		byte[] word = Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self.Args_len(), 0);
		Xol_lang_itm lang = ctx.Cur_page().Lang();
		boolean pass = false;
		try {pass = lang.Grammar().Grammar_eval(bfr, lang, word, argx);}
		catch (Exception e) {Err_.Noop(e);}
		if (!pass) Xot_invk_tkn.Print_not_found(bfr, ctx.Wiki().Ns_mgr(), this.Name());
	}
}	
