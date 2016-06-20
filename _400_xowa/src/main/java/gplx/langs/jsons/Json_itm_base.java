package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public abstract class Json_itm_base implements Json_itm {
	public abstract byte Tid();
	public void Ctor(int src_bgn, int src_end) {this.src_bgn = src_bgn; this.src_end = src_end;}
	public int Src_bgn() {return src_bgn;} private int src_bgn;
	public int Src_end() {return src_end;} protected int src_end;
	public abstract Object Data();
	public abstract byte[] Data_bry();
	public String Print_as_json() {Bry_bfr bfr = Bry_bfr_.New(); Print_as_json(bfr, 0); return bfr.To_str_and_clear();}
	public abstract void Print_as_json(Bry_bfr bfr, int depth);
	@gplx.Virtual public boolean Data_eq(byte[] comp) {return false;}
}
