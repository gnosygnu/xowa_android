package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public class Json_itm_int extends Json_itm_base {
	private final Json_doc doc;
	private byte[] data_bry; private int data; private boolean data_is_null = true;
	public Json_itm_int(Json_doc doc, int src_bgn, int src_end) {this.Ctor(src_bgn, src_end); this.doc = doc;}
	@Override public byte Tid() {return Json_itm_.Tid__int;}
	public int Data_as_int() {
		if (data_is_null) {
			data = doc.Utl_num_parser().Parse(doc.Src(), Src_bgn(), Src_end()).Rv_as_int();
			data_is_null = false;
		}
		return data;
	}
	@Override public Object Data() {return Data_as_int();}
	@Override public byte[] Data_bry() {if (data_bry == null) data_bry = Bry_.Mid(doc.Src(), this.Src_bgn(), this.Src_end()); return data_bry;}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add_mid(doc.Src(), this.Src_bgn(), this.Src_end());}
	public static Json_itm_int cast(Json_itm v) {return v == null || v.Tid() != Json_itm_.Tid__int ? null : (Json_itm_int)v;}
}
