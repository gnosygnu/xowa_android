package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_enum_ {
	public static byte To_tid_or_invalid(Hash_adp hash, byte[] url, String key) {
		Object rv_obj = hash.Get_by(key);
		if (rv_obj == null) {
			Gfo_usr_dlg_.Instance.Warn_many("", "", "unknown enum key for wikibase; url=~{0} key=~{1}", url, key);
			return Tid__invalid;
		}
		return ((Wbase_claim_enum)rv_obj).Tid();
	}
	public static byte To_tid_or_invalid(Hash_adp_bry hash, byte[] url, byte[] key) {
		Object rv_obj = hash.Get_by_bry(key);
		if (rv_obj == null) {
			Gfo_usr_dlg_.Instance.Warn_many("", "", "unknown enum key for wikibase; url=~{0} key=~{1}", url, key);
			return Tid__invalid;
		}
		return ((Wbase_claim_enum)rv_obj).Tid();
	}
	public static final byte Tid__invalid = Byte_.Max_value_127;
}