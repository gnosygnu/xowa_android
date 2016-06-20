package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.portal.*;
public class Xoh_page_mgr implements Gfo_invk {
	public Xoh_subpages_bldr Subpages_bldr() {return subpages_bldr;} private final    Xoh_subpages_bldr subpages_bldr = new Xoh_subpages_bldr();
	public boolean Font_enabled() {return font_enabled;} private boolean font_enabled = false;
	public void Font_enabled_(boolean v) {font_enabled = v;}
	public String Font_name() {return font_name;} private String font_name = "Arial";
	public float Font_size() {return font_size;} private float font_size = Font_size_default;
	public void Font_size_(float v) {
		font_size = v;
		this.Font_css_bry_update();
	}
	public Bry_fmtr Font_css_fmtr() {return font_css_fmtr;} private final    Bry_fmtr font_css_fmtr = Bry_fmtr.new_("body {font-family: ~{font_name}; font-size: ~{font_size}px;}", "font_name", "font_size");
	public Bry_fmtr Content_code_fmtr() {return content_code_fmtr;} private final    Bry_fmtr content_code_fmtr = Bry_fmtr.new_("<pre>~{page_text}</pre>", "page_text");
	private void Font_css_fmtr_(byte[] bry) {
		font_css_fmtr.Fmt_(bry);
		Font_css_bry_update();
	}
	public byte[] Font_css_bry() {return font_css_bry;}
	public void Font_css_bry_update() {
		font_css_bry = font_css_fmtr.Bld_bry_many(Bry_bfr_.New(), font_name, font_size);
	}	private byte[] font_css_bry = Bry_.Empty;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_font_name)) 				return font_name;
		else if	(ctx.Match(k, Invk_font_name_))				{font_name = m.ReadStr("v"); this.Font_css_bry_update();}
		else if	(ctx.Match(k, Invk_font_size)) 				return font_size;
		else if	(ctx.Match(k, Invk_font_size_))				{font_size = m.ReadFloat("v"); this.Font_css_bry_update();}
		else if	(ctx.Match(k, Invk_font_css_fmt)) 			return String_.new_u8(font_css_fmtr.Fmt());
		else if	(ctx.Match(k, Invk_font_css_fmt_)) 			Font_css_fmtr_(m.ReadBry("v"));
		else if	(ctx.Match(k, Invk_font_enabled)) 			return Yn.To_str(font_enabled);
		else if	(ctx.Match(k, Invk_font_enabled_)) 			font_enabled = m.ReadYn("v");
		else if	(ctx.Match(k, Invk_content_code_fmt))		return String_.new_u8(content_code_fmtr.Fmt());
		else if	(ctx.Match(k, Invk_content_code_fmt_))		content_code_fmtr.Fmt_(m.ReadBry("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_font_name = "font_name", Invk_font_name_ = "font_name_", Invk_font_size = "font_size", Invk_font_size_ = "font_size_"
	, Invk_font_css_fmt = "font_css_fmt", Invk_font_css_fmt_ = "font_css_fmt_", Invk_font_enabled = "font_enabled", Invk_font_enabled_ = "font_enabled_"
	, Invk_content_code_fmt = "content_code_fmt", Invk_content_code_fmt_ = "content_code_fmt_"
	;
	public static final float Font_size_default = 16;
}
