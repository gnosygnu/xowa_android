package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_anchorencode extends Pf_func_base {	// EX: {{anchorencode:a b}} -> a+b
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public int Id() {return Xol_kwd_grp_.Id_url_anchorencode;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_anchorencode().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] raw_bry = Eval_argx(ctx, src, caller, self); if (raw_bry == Bry_.Empty) return;
		Anchor_encode(bfr, ctx, raw_bry);
	}		
	public static void Anchor_encode(Bry_bfr bfr, Xop_ctx ctx, byte[] raw) {
		Pfunc_anchorencode_mgr mgr = ctx.Wiki().Parser_mgr().Anchor_encoder_mgr__dflt_or_new(ctx);
		try {mgr.Encode_anchor(bfr, ctx, raw);}
		finally {mgr.Used_(Bool_.N);}
	}
}
