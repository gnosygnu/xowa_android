package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public abstract class Xoh_head_itm__base {
	public abstract byte[] Key();
	public abstract int Flags();
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} public void Enabled_y_() {this.Enabled_(Bool_.Y);} public void Enabled_n_() {this.Enabled_(Bool_.N);} private boolean enabled;
	@gplx.Virtual public void Clear() {this.Enabled_(Bool_.N);}
	@gplx.Virtual public void Write_css_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_css_text(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_js_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_js_head_global(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_js_head_script(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_js_tail_script(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	@gplx.Virtual public void Write_js_window_onload(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {}
	public static final int
	  Flag__disabled			= 0
	, Flag__css_include			= 1
	, Flag__css_text			= 2
	, Flag__js_include			= 4
	, Flag__js_head_global		= 8
	, Flag__js_head_script		= 16
	, Flag__js_tail_script		= 32
	, Flag__js_window_onload	= 64
	;
	public static final int Idx__max	= 7;
}
/*
Position	// top, bottom
Targets		// mobile, desktop
Dependencies
Messages
*/
