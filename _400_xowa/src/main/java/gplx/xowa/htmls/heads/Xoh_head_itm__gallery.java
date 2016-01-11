package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
public class Xoh_head_itm__gallery extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__gallery;}
	@Override public int Flags() {return Flag__js_head_global;}
	@Override public void Write_js_head_global(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_js_global_ini_atr_val(Key_enabled			, true);
	}
	private static final byte[]
	  Key_enabled				= Bry_.new_a7("gallery-packed-enabled")
	;
}
