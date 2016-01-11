package gplx.xowa.guis.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.gfui.*;
public class Xol_font_info implements GfoInvkAble, GfoEvMgrOwner {
	public Xol_font_info(String name, float size, FontStyleAdp style) {this.name = name; this.size = size; this.style = style;}
	public GfoEvMgr			EvMgr() {if (evMgr == null) evMgr = GfoEvMgr.new_(this); return evMgr;} GfoEvMgr evMgr;
	public String Name() {return name;} public Xol_font_info Name_(String v) {name = v; Font_changed_pub(); return this;} private String name;
	public float Size() {return size;} public Xol_font_info Size_(float v) {size = v; Font_changed_pub(); return this;} private float size;
	public FontStyleAdp Style() {return style;} public Xol_font_info Style_(FontStyleAdp v) {style = v; Font_changed_pub(); return this;} private FontStyleAdp style;
	public Xol_font_info CloneNew() {return new Xol_font_info(name, size, style);}
	public FontAdp XtoFontAdp() {return FontAdp.new_(name, size, style);}
	public boolean Eq(FontAdp font) {return String_.Eq(name, font.Name()) && size == font.Size() && style.Val() == font.Style().Val();}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_name))			return name;
		else if (ctx.Match(k, Invk_name_))			Name_(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_size))			return size;
		else if	(ctx.Match(k, Invk_size_))			Size_(m.ReadFloat("v"));
		else if	(ctx.Match(k, Invk_style_))			Style_(FontStyleAdp_.parse(m.ReadStr("v")));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_name = "name", Invk_name_ = "name_", Invk_size = "size", Invk_size_ = "size_", Invk_style_ = "style_";
	public static final String Font_changed = "font_changed";
	private void Font_changed_pub() {GfoEvMgr_.PubObj(this, Font_changed, "font", this);}
}	
