package gplx.xowa.xtns.massMessage; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Message_target_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_massMessage_target;}
	@Override public Pf_func New(int id, byte[] name) {return new Message_target_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self); if (argx == null) return;
		bfr.Add(Xop_tkn_.Lnki_bgn).Add(argx).Add(Xop_tkn_.Lnki_end);	// TODO: evaluate 2nd arg; {{#target:A|en.wikipedia.org}}
	}
	public static final Message_target_func Instance = new Message_target_func(); Message_target_func() {}
}
