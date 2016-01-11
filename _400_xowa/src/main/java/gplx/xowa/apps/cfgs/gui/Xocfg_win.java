package gplx.xowa.apps.cfgs.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.guis.langs.*;
public class Xocfg_win implements GfoInvkAble {
	public Xocfg_win(Xoae_app app) {}
	public Xol_font_info Font() {return font;} private Xol_font_info font = new Xol_font_info("Arial", 8, gplx.gfui.FontStyleAdp_.Plain);
	public Bry_fmtr Search_box_fmtr() {return search_box_fmtr;} private Bry_fmtr search_box_fmtr = Bry_fmtr.new_("Special:Allpages?from=", "search");
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_font))					return font;
		else if	(ctx.Match(k, Invk_search_box_fmt_))		search_box_fmtr.Fmt_(m.ReadBry("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_search_box_fmt_ = "search_box_fmt_", Invk_font = "font";
	public static final float Font_size_default = 8;
}
