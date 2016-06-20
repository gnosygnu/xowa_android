package gplx.core.net.qargs; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public class Gfo_qarg_enum_mgr {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	public Gfo_qarg_enum_mgr(Gfo_qarg_enum_itm... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Gfo_qarg_enum_itm itm = ary[i];
			hash.Add_bry_obj(itm.Key(), itm);
		}
	}
	public Gfo_qarg_enum_itm Get(byte[] key) {return (Gfo_qarg_enum_itm)hash.Get_by_bry(key);}
}
