package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.xowa.wikis.domains.*;
public class Xoa_cfg_grp_tid {
	public byte Tid() {return tid;} private byte tid;
	public byte[] Key() {return key;} private byte[] key;
	public int Wiki_tid() {return wiki_tid;} private int wiki_tid;
	public static final byte Tid_null = 0, Tid_all = 1, Tid_type = 2, Tid_wiki = 3, Tid_app = 4;
	public static final String Key_app_str = "app";
	public static final    byte[] Key_all_bry = Bry_.new_a7("*"), Key_app_bry = Bry_.new_a7(Key_app_str);
	public static Xoa_cfg_grp_tid parse(byte[] key) {
		Xoa_cfg_grp_tid rv = (Xoa_cfg_grp_tid)factory.Get_by_bry(key);
		if (rv == null) {
			rv = new Xoa_cfg_grp_tid();
			if		(Bry_.Eq(key, Key_all_bry)) rv.tid = Tid_all;
			else if	(Bry_.Eq(key, Key_app_bry)) rv.tid = Tid_app;
			else {
				Xow_domain_itm wiki_type = Xow_domain_itm_.parse(key);
				if (wiki_type.Domain_type_id() == Xow_domain_tid_.Tid__other)
					rv.tid = Tid_wiki;
				else {
					rv.tid = Tid_type;
					rv.wiki_tid = wiki_type.Domain_type_id();
				}				
			}
			rv.key = key;
			factory.Add(key, rv);
		}
		return rv;
	}	private static Hash_adp_bry factory = Hash_adp_bry.cs();
}
