package gplx.xowa.xtns.titleBlacklists; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.scribunto.*;
public class Blacklist_xtn_mgr extends Xox_mgr_base {
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final byte[] XTN_KEY = Bry_.new_a7("TitleBlacklist");
	@Override public void Xtn_init_by_app(Xoae_app app) {
		Scrib_xtn_mgr scrib_xtn = (Scrib_xtn_mgr)app.Xtn_mgr().Get_or_fail(Scrib_xtn_mgr.XTN_KEY);
		scrib_xtn.Lib_mgr().Add(new Blacklist_scrib_lib());
	}
	@Override public Xox_mgr Clone_new() {return new Blacklist_xtn_mgr();}
}
