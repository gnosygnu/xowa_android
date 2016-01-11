package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public class Json_itm_tmp implements Json_itm {	// TEST:
	public Json_itm_tmp(byte tid, String data) {this.tid = tid; this.data = data;}
	public byte Tid() {return tid;} private byte tid;
	public byte[] Data_bry() {return Bry_.new_u8(Object_.Xto_str_strict_or_empty(data));}
	public int Src_bgn() {return -1;}
	public int Src_end() {return -1;}
	public Object Data() {return data;} private String data;
	public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add_str_u8(data);}
	public boolean Data_eq(byte[] comp) {return false;}
	public void Clear() {}
	public static Json_itm new_str_(String v)	{return new Json_itm_tmp(Json_itm_.Tid__str, "\"" + v + "\"");}
	public static Json_itm new_int_(int v)		{return new Json_itm_tmp(Json_itm_.Tid__int, Int_.To_str(v));}
}
