package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public class Json_itm_decimal extends Json_itm_base {
	private final    Json_doc doc; private Decimal_adp data; private byte[] data_bry;
	public Json_itm_decimal(Json_doc doc, int src_bgn, int src_end) {this.Ctor(src_bgn, src_end); this.doc = doc;}
	@Override public byte Tid() {return Json_itm_.Tid__decimal;}
	@Override public Object Data() {return this.Data_as_decimal();}
	@Override public byte[] Data_bry() {
		if (data_bry == null) data_bry = Bry_.Mid(doc.Src(), this.Src_bgn(), this.Src_end());
		return data_bry;
	}
	public Decimal_adp Data_as_decimal() {
		if (data == null)
			data = Decimal_adp_.parse(String_.new_a7(this.Data_bry()));
		return data;
	}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add_mid(doc.Src(), this.Src_bgn(), this.Src_end());}
}
