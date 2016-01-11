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
	public static final Json_itm_null Null = new Json_itm_null();
}
class Json_itm_bool extends Json_itm_base {
	private boolean data;
	public Json_itm_bool(boolean data) {this.data = data; this.Ctor(-1, -1);}
	@Override public byte Tid() {return Json_itm_.Tid__bool;}
	@Override public Object Data() {return data;}
	@Override public byte[] Data_bry() {return data ? Json_itm_.Bry__true : Json_itm_.Bry__false;}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add(data ? Json_itm_.Bry__true: Json_itm_.Bry__false);}
	public static final Json_itm_bool Bool_n = new Json_itm_bool(false), Bool_y = new Json_itm_bool(true);
}
class Json_itm_decimal extends Json_itm_base {
	private final Json_doc doc; private Decimal_adp data; private byte[] data_bry;
	public Json_itm_decimal(Json_doc doc, int src_bgn, int src_end) {this.Ctor(src_bgn, src_end); this.doc = doc;}
	@Override public byte Tid() {return Json_itm_.Tid__decimal;}
	@Override public Object Data() {
		if (data == null)
			data = Decimal_adp_.parse(String_.new_a7(this.Data_bry()));
		return data;
	}
	@Override public byte[] Data_bry() {
		if (data_bry == null) data_bry = Bry_.Mid(doc.Src(), this.Src_bgn(), this.Src_end());
		return data_bry;
	}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add_mid(doc.Src(), this.Src_bgn(), this.Src_end());}
}
