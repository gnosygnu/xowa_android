package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public class Json_itm_bool extends Json_itm_base {
	private boolean data;
	public Json_itm_bool(boolean data) {this.data = data; this.Ctor(-1, -1);}
	@Override public byte Tid() {return Json_itm_.Tid__bool;}
	public boolean Data_as_bool() {return data;}
	@Override public Object Data() {return data;}
	@Override public byte[] Data_bry() {return data ? Json_itm_.Bry__true : Json_itm_.Bry__false;}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {bfr.Add(data ? Json_itm_.Bry__true: Json_itm_.Bry__false);}
	public static final    Json_itm_bool Bool_n = new Json_itm_bool(false), Bool_y = new Json_itm_bool(true);
}
