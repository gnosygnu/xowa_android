package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public interface Json_itm {
	byte		Tid();
	int			Src_bgn();
	int			Src_end();
	Object		Data();
	byte[]		Data_bry();
	void		Print_as_json(Bry_bfr bfr, int depth);
	boolean		Data_eq(byte[] comp);
}
class Json_itm_null extends Json_itm_base {
	Json_itm_null() {this.Ctor(-1, -1);}
	@Override public byte Tid() {return Json_itm_.Tid__null;}
	@Override public Object Data() {return null;}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add(Object_.Bry__null);}
	@Override public byte[] Data_bry() {return Object_.Bry__null;}
	public static final    Json_itm_null Null = new Json_itm_null();
}
