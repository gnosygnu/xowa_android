package gplx.xowa.wikis.tdbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.primitives.*;
public class ByteAry_fil {
	public List_adp Itms() {return itms;} List_adp itms = List_adp_.new_();
	public Io_url Fil() {return fil;} Io_url fil;
	public byte[] Raw_bry() {return raw_bry;} private byte[] raw_bry = Bry_.Empty;
	public int Raw_len() {return raw_len.Val();} Int_obj_ref raw_len = Int_obj_ref.zero_();
	public int Raw_max() {return raw_max;} private int raw_max = Io_mgr.Len_mb;
	public ByteAry_fil Ini_file(Io_url fil) {
		this.fil = fil;
		raw_bry = Io_mgr.Instance.LoadFilBry_reuse(fil, raw_bry, raw_len);
		return this;
	}
	public Object Xto_itms(Class<?> itm_type) {
		Object rv = itms.To_ary(itm_type);
		itms.Clear();
		if (raw_bry.length > raw_max) raw_bry = Bry_.Empty;
		raw_len.Val_zero_();
		return rv;
	}
	public static final ByteAry_fil Instance = new ByteAry_fil(); ByteAry_fil() {}
}
