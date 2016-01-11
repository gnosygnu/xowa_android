package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.primitives.*;
public class Wdata_dict_utl {
	public static byte Get_tid_or_invalid(byte[] qid, Hash_adp_bry dict, byte[] key) {
		Object rv_obj = dict.Get_by_bry(key);
		if (rv_obj == null) {
			Gfo_usr_dlg_.Instance.Warn_many("", "", "unknown wikidata key; qid=~{0} key=~{1}", String_.new_u8(qid), String_.new_u8(key));
			return Tid_invalid;
		}
		return ((Byte_obj_val)rv_obj).Val();
	}
	public static final byte Tid_invalid = Byte_.Max_value_127;
}
