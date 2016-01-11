package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.xowa.parsers.lnkis.cfgs.*;
public class Xowc_parser implements GfoInvkAble {
	public Xowc_parser(Xowe_wiki wiki) {
		lnki_cfg = new Xoc_lnki_cfg(wiki);
	}
	public Xoc_lnki_cfg Lnki_cfg() {return lnki_cfg;} private Xoc_lnki_cfg lnki_cfg;
	public Xowc_xtns Xtns() {return xtns;} private Xowc_xtns xtns = new Xowc_xtns();
	public boolean Display_title_restrict() {return display_title_restrict;} public void Display_title_restrict_(boolean v) {display_title_restrict = v;} private boolean display_title_restrict = true;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_xtns))						return xtns;
		else if	(ctx.Match(k, Invk_lnki))						return lnki_cfg;
		else if	(ctx.Match(k, Invk_display_title_restrict))		return display_title_restrict;
		else if	(ctx.Match(k, Invk_display_title_restrict_))	display_title_restrict = m.ReadYn("v");
		else													return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_xtns = "xtns", Invk_lnki = "lnki", Invk_display_title_restrict = "display_title_restrict", Invk_display_title_restrict_ = "display_title_restrict_";
}
