package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.core.primitives.*; import gplx.core.net.*;
public class Xoa_url_arg_mgr {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	private final    Xoa_url_enum_mgr enm_mgr;
	public Xoa_url_arg_mgr(Xoa_url_enum_mgr enm_mgr) {this.enm_mgr = enm_mgr;}
	public Xoa_url_arg_mgr Init(Gfo_qarg_itm[] args) {
		hash.Clear();
		int len = args.length;
		for (int i = 0; i < len; ++i) {
			Gfo_qarg_itm arg = args[i];
			hash.Add_bry_obj(arg.Key_bry(), arg);
		}
		return this;
	}
	public int Read_enm_or_neg1(byte[] key) {
		Xoa_url_enum_itm enm = enm_mgr.Get(key);				if (enm == null) return -1;
		Gfo_qarg_itm arg = (Gfo_qarg_itm)hash.Get_by_bry(key);	if (arg == null) return -1;
		return enm.Get_as_int_or(arg.Val_bry(), -1);
	}
	public byte[] Read_bry_or_empty(byte[] key) {return Read_bry_or(key, Bry_.Empty);}
	public byte[] Read_bry_or_null(String key) {return Read_bry_or_null(Bry_.new_u8(key));}
	public byte[] Read_bry_or_null(byte[] key) {return Read_bry_or(key, null);}
	public byte[] Read_bry_or(byte[] key, byte[] or) {
		Gfo_qarg_itm arg = (Gfo_qarg_itm)hash.Get_by_bry(key);
		return arg == null ? or : arg.Val_bry();
	}
	public String Read_str_or_null(String key) {return Read_str_or_null(Bry_.new_u8(key));}
	public String Read_str_or_null(byte[] key) {
		Gfo_qarg_itm arg = (Gfo_qarg_itm)hash.Get_by_bry(key);
		return arg == null ? null : String_.new_u8(arg.Val_bry());
	}
}
