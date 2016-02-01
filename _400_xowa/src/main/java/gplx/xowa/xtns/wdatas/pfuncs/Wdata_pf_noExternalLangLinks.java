package gplx.xowa.xtns.wdatas.pfuncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Wdata_pf_noExternalLangLinks extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_noexternallanglinks;}
	@Override public Pf_func New(int id, byte[] name) {return new Wdata_pf_noExternalLangLinks().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		ctx.Page().Wdata_external_lang_links().Parse(ctx, src, caller, self, this);
	}
	public static void Print_self(Gfo_usr_dlg usr_dlg, Bry_bfr bfr, byte[] src, Xot_invk self, String warn_cls, String warn_fmt, Object... args) {
		bfr.Add_mid(src, self.Src_bgn(), self.Src_end());
		usr_dlg.Warn_many(GRP_KEY, warn_cls, warn_fmt, args);
	}
	static final String GRP_KEY = "xowa.xtns.wdata.noexternallanglinks";
}
