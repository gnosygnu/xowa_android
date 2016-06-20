package gplx.xowa.xtns.indicators; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.htmls.*; import gplx.xowa.wikis.*;
public class Indicator_xtn_mgr extends Xox_mgr_base {
	public Indicator_xtn_mgr() {
	}
	@Override public boolean Enabled_default() {return false;}
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("Indicator");
	@Override public Xox_mgr Xtn_clone_new() {return new Indicator_xtn_mgr();}
}
