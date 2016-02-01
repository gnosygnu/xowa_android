package gplx.xowa.xtns.geoCrumbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Geoc_isin_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_geoCrumbs_isin;}
	@Override public Pf_func New(int id, byte[] name) {return new Geoc_isin_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] ttl_bry = Eval_argx(ctx, src, caller, self);
		Xowe_wiki wiki = ctx.Wiki();
		Xoa_ttl ttl = Xoa_ttl.parse(wiki, ttl_bry); if (ttl == null) return;
		byte[] lnki_ttl = Bry_.Add(Xop_tkn_.Lnki_bgn, ttl_bry, Xop_tkn_.Lnki_end);		// make "[[ttl]]"
		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_b128();
		wiki.Parser_mgr().Main().Parse_text_to_html(tmp_bfr, ctx.Page(), false, lnki_ttl);
		ctx.Page().Html_data().Content_sub_(tmp_bfr.To_bry_and_rls());
	}
	public static final Geoc_isin_func Instance = new Geoc_isin_func();
}
