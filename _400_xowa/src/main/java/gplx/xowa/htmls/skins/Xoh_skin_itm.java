package gplx.xowa.htmls.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.brys.fmtrs.*;
public class Xoh_skin_itm implements Gfo_invk {
	private final    Bry_fmtr fmtr = Bry_fmtr.new_();
	public Xoh_skin_itm(String key, String fmt) {this.key = key; fmtr.Fmt_(fmt);}
	public String Key() {return key;} private final    String key;
	public void Fmt_(String v) {fmtr.Fmt_(v);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_fmt))		return String_.new_u8(fmtr.Fmt());
		else if	(ctx.Match(k, Invk_fmt_))		fmtr.Fmt_(m.ReadStr("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_fmt = "fmt", Invk_fmt_ = "fmt_"
	;
}
