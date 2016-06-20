package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_urlencode extends Pf_func_base {	// EX: {{urlencode:a b}} -> a+b
	@Override public int Id() {return Xol_kwd_grp_.Id_url_urlencode;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_urlencode().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self); if (argx == Bry_.Empty) return;
		gplx.langs.htmls.encoders.Gfo_url_encoder_.Http_url.Encode(urlEncodeBfr, argx);
		bfr.Add_bfr_and_preserve(urlEncodeBfr);
		urlEncodeBfr.Clear();
	}	private Bry_bfr urlEncodeBfr = Bry_bfr_.New_w_size(128);
}
