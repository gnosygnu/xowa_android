package gplx.core.intls; import gplx.*; import gplx.core.*;
public class Gfo_i18n_mgr {
	public String Dflt() {return dflt;} private String dflt = "en";
	public Gfo_i18n_mgr Add_txt_dflt(String key, String val) {return this;}
	public Gfo_i18n_mgr Add_txt_one(String key, String lng, String val) {return this;}
	public Gfo_i18n_mgr Add_txt_many(String key, String... ary) {return this;}
}
class Gfo_i18n_lng {
	private Hash_adp_bry hash = Hash_adp_bry.cs();
	public Gfo_i18n_lng(String lng) {this.lng = lng;}
	public String Lng() {return lng;} private final String lng;
	public void Add(int src, byte[] key, byte[] val, boolean val_fmt_exists, Gfo_i18n_val_cmd val_cmd) {
		Gfo_i18n_itm itm = new Gfo_i18n_itm(src, key, val, val_fmt_exists, val_cmd);
		hash.Add_bry_obj(key, itm);
	}
	public byte[] Bld_to_bry_none(byte[] key) {
		Gfo_i18n_itm itm = (Gfo_i18n_itm)hash.Get_by_bry(key);
		return itm.Bld_none();
	}
	public byte[] Bld_to_bry_many(byte[] key, Object... args) {
		Gfo_i18n_itm itm = (Gfo_i18n_itm)hash.Get_by_bry(key);
		return itm.Bld_many(args);
	}
}
