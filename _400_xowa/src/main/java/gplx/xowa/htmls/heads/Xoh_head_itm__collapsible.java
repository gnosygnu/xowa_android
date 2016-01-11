package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public class Xoh_head_itm__collapsible extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__collapsible;}
	@Override public int Flags() {return Flag__js_head_global;}
	@Override public void Write_js_head_global(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_js_global_ini_atr_val(Key_enabled		, true);
		wtr.Write_js_global_ini_atr_val(Key_collapsed	, app.Api_root().Html().Modules().Collapsible().Collapsed());
		wtr.Write_js_global_ini_atr_msg(wiki			, Key_collapse);
		wtr.Write_js_global_ini_atr_msg(wiki			, Key_expand);
	}
	private static final byte[]
	  Key_enabled				= Bry_.new_a7("collapsible-enabled")
	, Key_collapsed				= Bry_.new_a7("collapsible-collapsed")
	, Key_collapse				= Bry_.new_a7("collapsible-collapse")
	, Key_expand				= Bry_.new_a7("collapsible-expand")
	;
}
