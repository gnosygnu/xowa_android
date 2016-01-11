package gplx.xowa.langs.msgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xow_mainpage_finder {
	public static byte[] Find_or(Xowe_wiki wiki, byte[] or) {
		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_b512();
		Xol_msg_itm msg_itm = Xol_msg_mgr_.Get_msg_itm(tmp_bfr, wiki, wiki.Lang(), Msg_mainpage);
		byte[] rv = msg_itm.Src_is_missing()
			? or
			: Xol_msg_mgr_.Get_msg_val(tmp_bfr, wiki, msg_itm, Bry_.Ary_empty)
			;
		tmp_bfr.Mkr_rls();
		return rv;
	}
	public static final byte[] Msg_mainpage = Bry_.new_a7("mainpage");
}
