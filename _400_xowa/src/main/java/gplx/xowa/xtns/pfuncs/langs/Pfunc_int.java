package gplx.xowa.xtns.pfuncs.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.langs.phps.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*; import gplx.xowa.langs.msgs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_int extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_i18n_int;}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] msg_key = Eval_argx(ctx, src, caller, self);
		Xowe_wiki wiki = ctx.Wiki();
		Xol_lang_itm page_lang = ctx.Page().Lang();
		byte[][] args_ary = Bry_.Ary_empty;
		int args_len = self.Args_len();
		if (args_len > 0) {
			args_ary = new byte[args_len][];
			for (int i = 0; i < args_len; i++)
				args_ary[i] = Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self.Args_len(), i);
		}
		byte[] msg_val = Xol_msg_mgr_.Get_msg_val(wiki, page_lang, msg_key, args_ary);
		bfr.Add(msg_val);
	}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_int().Name_(name);}
}
